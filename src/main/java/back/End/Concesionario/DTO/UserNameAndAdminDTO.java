package back.End.Concesionario.DTO;

import back.End.Concesionario.Model.Enum.Rol;
import lombok.Data;

@Data
public class UserNameAndAdminDTO {
    private String username;
    private Rol rol;

}
