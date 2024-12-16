package back.End.Concesionario.DTO;

// Importación de enumeración y librerías necesarias
import back.End.Concesionario.Model.Enum.Rol; // Importa la enumeración Rol que define los roles de usuario
import lombok.Data; // Genera automáticamente getters, setters, equals, hashCode y toString

// Anotación Lombok para reducir código repetitivo
@Data // Genera automáticamente getters, setters, equals, hashCode y toString para todos los atributos
public class RegisterDTO {
    // Atributos del DTO (Data Transfer Object) utilizados para registrar un nuevo usuario
    private String username; // Nombre de usuario elegido para el registro
    private String password; // Contraseña del usuario
    private String email; // Dirección de correo electrónico del usuario
    private Rol rol; // Rol del usuario (admin, cliente, etc.) definido mediante la enumeración Rol
}
