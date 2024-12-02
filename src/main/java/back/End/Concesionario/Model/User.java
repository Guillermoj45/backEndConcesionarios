package back.End.Concesionario.Model;

import back.End.Concesionario.Model.Enum.Rol;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", length = 30)
    String name;

    @Column(name = "email", length = 255, unique = true)
    String email;

    @Column(name = "password", length = 50)
    String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rol")
    Rol rol;
}
