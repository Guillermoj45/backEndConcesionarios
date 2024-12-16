package back.End.Concesionario.security;

// Importación de las clases necesarias
import back.End.Concesionario.Model.User; // Modelo User que representa un usuario
import back.End.Concesionario.Repository.UserRepository; // Repositorio de usuarios
import lombok.RequiredArgsConstructor; // Anotación para generar el constructor con los argumentos necesarios
import org.springframework.context.annotation.Bean; // Anotación para definir un Bean en el contexto de Spring
import org.springframework.context.annotation.Configuration; // Indica que esta clase es una configuración de Spring
import org.springframework.security.authentication.AuthenticationManager; // Interfaz para gestionar la autenticación
import org.springframework.security.authentication.AuthenticationProvider; // Interfaz para proporcionar un método de autenticación
import org.springframework.security.authentication.dao.DaoAuthenticationProvider; // Implementación de AuthenticationProvider usando un DAO
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration; // Configuración de la autenticación
import org.springframework.security.core.Authentication; // Interfaz para manejar la autenticación
import org.springframework.security.core.context.SecurityContextHolder; // Clase que proporciona el contexto de seguridad actual
import org.springframework.security.core.userdetails.UserDetails; // Interfaz para obtener detalles de un usuario autenticado
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException; // Excepción lanzada cuando no se encuentra un usuario
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Codificador de contraseñas usando BCrypt
import org.springframework.security.crypto.password.PasswordEncoder; // Interfaz para codificar contraseñas
import org.springframework.web.context.annotation.RequestScope; // Anotación que indica que el Bean tiene un alcance por solicitud

@Configuration // Marca la clase como una clase de configuración
@RequiredArgsConstructor // Genera el constructor con los campos necesarios
public class ApplicationConfig {

    private final UserRepository userRepository; // Repositorio de usuarios inyectado

    // Bean que define un UserDetailsService, usado para cargar los detalles del usuario durante la autenticación
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findTopByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // Bean que define un AuthenticationProvider, usado para autenticar al usuario con los detalles y la contraseña
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService()); // Asocia el UserDetailsService
        authenticationProvider.setPasswordEncoder(passwordEncoder()); // Establece el codificador de contraseñas
        return authenticationProvider;
    }

    // Bean que define el AuthenticationManager, usado para gestionar la autenticación
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Bean que define un PasswordEncoder para codificar las contraseñas
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // Implementación de BCrypt para la codificación de contraseñas
    }

    // Bean que proporciona el usuario autenticado en el contexto de la solicitud
    @Bean
    @RequestScope // El Bean tiene un alcance por solicitud
    public User authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Obtiene la autenticación actual
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) { // Si la autenticación es válida
            String username = ((UserDetails) authentication.getPrincipal()).getUsername(); // Obtiene el nombre de usuario
            return userRepository.findTopByUsername(username).orElse(null); // Devuelve el usuario autenticado
        }
        return null; // Si no hay autenticación, retorna null
    }

}
