package back.End.Concesionario.security;

import lombok.RequiredArgsConstructor; // Inyecta las dependencias del constructor
import org.springframework.context.annotation.Bean; // Para definir métodos que generan beans para el contexto de Spring
import org.springframework.context.annotation.Configuration; // Marca esta clase como configuración de Spring
import org.springframework.security.authentication.AuthenticationProvider; // Interfaz para autenticar a los usuarios
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; // Habilita seguridad en métodos
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // Configuración de seguridad HTTP
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; // Habilita la seguridad web en la aplicación
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer; // Clase base para configuradores HTTP
import org.springframework.security.web.SecurityFilterChain; // Permite configurar el filtro de seguridad
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Filtro de autenticación predeterminado para nombre de usuario y contraseña

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS; // Configuración de política de sesiones sin estado

@Configuration // Marca la clase como configuración de Spring
@EnableWebSecurity // Habilita la seguridad web para la aplicación
@EnableMethodSecurity // Habilita la seguridad basada en métodos (como @PreAuthorize)
@RequiredArgsConstructor // Genera el constructor necesario para inyectar las dependencias automáticamente
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter; // Filtro de autenticación JWT
    private final AuthenticationProvider authenticationProvider; // Proveedor de autenticación

    // Define el filtro de seguridad para la aplicación
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilita la protección CSRF (Cross-Site Request Forgery)
                .authorizeHttpRequests(req -> // Configura las autorizaciones de las solicitudes HTTP
                        req
                                .requestMatchers("/api/user/login").permitAll() // Permite el acceso público a la ruta de login
                                .requestMatchers("/api/user").permitAll() // Permite el acceso público a la ruta de registro de usuario
                                .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS)) // Define que la aplicación no mantendrá el estado de las sesiones (sin estado)
                .authenticationProvider(authenticationProvider) // Usa el proveedor de autenticación configurado
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Añade el filtro de autenticación JWT antes del filtro de autenticación estándar
        ;

        return http.build(); // Devuelve la configuración construida del filtro de seguridad
    }

}
