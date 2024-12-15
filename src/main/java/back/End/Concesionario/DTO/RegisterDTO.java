package back.End.Concesionario.DTO;

import back.End.Concesionario.Model.Enum.Rol;
import lombok.Data;
import lombok.Getter;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String email;
    private Rol rol;
}
