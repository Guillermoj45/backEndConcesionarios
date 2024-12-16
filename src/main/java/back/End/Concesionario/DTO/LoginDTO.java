package back.End.Concesionario.DTO;

// Importaciones de Lombok para generar métodos de acceso (getters y setters)
import lombok.Getter; // Genera automáticamente los métodos getter para todos los atributos
import lombok.Setter; // Genera automáticamente los métodos setter para todos los atributos

// Anotaciones de Lombok para automatizar la creación de métodos
@Getter // Genera los métodos getter para los atributos de la clase
@Setter // Genera los métodos setter para los atributos de la clase
public class LoginDTO {
    // Atributos del DTO (Data Transfer Object) que se utilizan para manejar los datos de inicio de sesión
    private String username; // Nombre de usuario o identificador del cliente
    private String password; // Contraseña asociada al usuario
}
