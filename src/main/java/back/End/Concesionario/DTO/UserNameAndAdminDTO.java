package back.End.Concesionario.DTO;

// Importación de la enumeración Rol y Lombok
import back.End.Concesionario.Model.Enum.Rol; // Importa la enumeración Rol que define los roles de usuario
import lombok.Data; // Anotación de Lombok para generar getters, setters, equals, hashCode y toString automáticamente

// Anotación Lombok para generar automáticamente los métodos getter, setter, equals, hashCode y toString
@Data
public class UserNameAndAdminDTO {
    // Atributos que representan el nombre de usuario y el rol del usuario
    private String username; // Nombre de usuario
    private Rol rol; // Rol del usuario (por ejemplo, admin o cliente)
}
