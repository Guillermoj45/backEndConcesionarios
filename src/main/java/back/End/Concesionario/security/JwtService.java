package back.End.Concesionario.security;

import io.jsonwebtoken.Claims; // Clase de la librería JWT para representar las reclamaciones del token
import io.jsonwebtoken.Jwts; // Utilidades para crear y leer tokens JWT
import io.jsonwebtoken.SignatureAlgorithm; // Algoritmos para firmar tokens
import io.jsonwebtoken.io.Decoders; // Herramientas para decodificar datos
import io.jsonwebtoken.security.Keys; // Clases para trabajar con claves de seguridad
import org.springframework.beans.factory.annotation.Value; // Para inyectar valores desde el archivo de configuración
import org.springframework.security.core.userdetails.UserDetails; // Interfaz que proporciona detalles de un usuario autenticado
import org.springframework.stereotype.Service; // Anotación para marcar esta clase como un servicio de Spring

import java.security.Key; // Clase para representar una clave de seguridad
import java.util.Date; // Clase que maneja fechas
import java.util.HashMap; // Clase para estructuras de datos clave-valor
import java.util.Map; // Interfaz para colecciones clave-valor
import java.util.function.Function; // Interfaz funcional para la manipulación de funciones

@Service // Marca esta clase como un servicio de Spring que se gestionará automáticamente
public class JwtService {

    @Value("${JWT.SECRET}") // Inyecta el valor de la clave secreta desde el archivo de propiedades
    private String SECRET_KEY; // Clave secreta utilizada para firmar el JWT

    // Método para extraer el nombre de usuario del token
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject); // Extrae el "subject" del JWT
    }

    // Método genérico para extraer una reclamación del token
    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt); // Extrae todas las reclamaciones del token
        return claimsResolver.apply(claims); // Aplica la función al conjunto de reclamaciones
    }

    // Método para generar un token JWT para un usuario
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails); // Llama a la versión sobrecargada del método
    }

    // Método para generar un token JWT con reclamaciones adicionales
    public String generateToken(
            Map<String, Object> extraClaims, // Reclamaciones adicionales a incluir en el token
            UserDetails userDetails // Detalles del usuario para incluir en el token
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims) // Establece las reclamaciones adicionales
                .setSubject(userDetails.getUsername()) // Establece el nombre de usuario como el "subject"
                .setIssuedAt(new Date(System.currentTimeMillis())) // Establece la fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Establece la fecha de expiración (10 horas)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Firma el token con la clave secreta y el algoritmo HS256
                .compact(); // Crea el token compacto
    }

    // Método para verificar si el token es válido
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // Extrae el nombre de usuario del token
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token); // Verifica si el token no ha expirado y el nombre de usuario coincide
    }

    // Método para verificar si el token ha expirado
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); // Compara la fecha de expiración con la fecha actual
    }

    // Método para extraer la fecha de expiración del token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration); // Extrae la fecha de expiración del token
    }

    // Método para extraer todas las reclamaciones del token
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder() // Construye el parser
                .setSigningKey(getSignInKey()) // Establece la clave secreta para verificar la firma
                .build()
                .parseClaimsJws(token) // Analiza el token JWT
                .getBody(); // Extrae el cuerpo de las reclamaciones
    }

    // Método para obtener la clave secreta para firmar los tokens
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // Decodifica la clave secreta desde Base64
        return Keys.hmacShaKeyFor(keyBytes); // Genera una clave HMAC para la firma del token
    }

}
