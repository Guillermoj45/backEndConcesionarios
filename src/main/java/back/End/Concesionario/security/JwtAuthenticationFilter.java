package back.End.Concesionario.security;

// Importación de las clases necesarias
import jakarta.servlet.FilterChain; // Interfaz para la cadena de filtros en una solicitud
import jakarta.servlet.ServletException; // Excepción que puede lanzarse durante el procesamiento de una solicitud
import jakarta.servlet.http.HttpServletRequest; // Clase que representa una solicitud HTTP
import jakarta.servlet.http.HttpServletResponse; // Clase que representa una respuesta HTTP
import lombok.NonNull; // Anotación para asegurar que un argumento no es nulo
import lombok.RequiredArgsConstructor; // Anotación para generar el constructor con los argumentos necesarios
import org.springframework.security.core.context.SecurityContextHolder; // Clase que proporciona el contexto de seguridad actual
import org.springframework.security.core.userdetails.UserDetails; // Interfaz para obtener detalles de un usuario autenticado
import org.springframework.security.core.userdetails.UserDetailsService; // Interfaz para cargar un usuario por nombre
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Implementación de la autenticación con nombre de usuario y contraseña
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource; // Fuente para generar detalles de autenticación
import org.springframework.stereotype.Component; // Anotación para marcar la clase como un componente de Spring
import org.springframework.web.filter.OncePerRequestFilter; // Filtro que garantiza que se ejecute solo una vez por solicitud

import java.io.IOException; // Excepción que puede lanzarse durante la entrada y salida de datos

@Component // Marca la clase como un componente de Spring para que sea gestionada por el contenedor de Spring
@RequiredArgsConstructor // Genera el constructor con los campos necesarios
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService; // Servicio para gestionar JWT (JSON Web Token)
    private final UserDetailsService userDetailsService; // Servicio para cargar los detalles del usuario

    // Método sobrescrito para filtrar la solicitud y autenticar al usuario basado en el JWT
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, // Solicitud HTTP que se está procesando
            @NonNull HttpServletResponse response, // Respuesta HTTP para enviar al cliente
            @NonNull FilterChain filterChain // Cadena de filtros que permite continuar con el proceso
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization"); // Obtiene el encabezado de autorización
        final String jwt; // Variable para almacenar el JWT extraído
        final String username; // Variable para almacenar el nombre de usuario extraído del JWT

        // Si no hay encabezado o no empieza con "Bearer ", pasa al siguiente filtro
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7); // Extrae el JWT del encabezado, eliminando "Bearer "
        username = jwtService.extractUsername(jwt); // Extrae el nombre de usuario del JWT

        // Si el nombre de usuario es válido y no hay autenticación en el contexto de seguridad
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Carga los detalles del usuario desde el servicio de detalles de usuario
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Verifica si el JWT es válido para el usuario
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Si el token es válido, crea un token de autenticación
                final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, // Detalles del usuario
                        null, // Sin credenciales adicionales (como la contraseña)
                        userDetails.getAuthorities() // Autoridades (roles) del usuario
                );

                // Establece los detalles de la autenticación desde la solicitud HTTP
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Establece la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Pasa la solicitud y respuesta al siguiente filtro en la cadena
        filterChain.doFilter(request, response);
    }

}
