package back.End.Concesionario.DTO;

import lombok.Data;
import lombok.Getter;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String email;
}
