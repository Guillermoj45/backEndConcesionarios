package back.End.Concesionario.Model;

import back.End.Concesionario.Model.Enum.Rol;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", unique = true, length = 30)
    String username;

    @JsonIgnore
    @Column(name = "email", unique = true)
    String email;

    @JsonIgnore
    @Column(name = "password")
    String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rol")
    Rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }


    @Override
    public boolean isAccountNonExpired() {
        // return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // return UserDetails.super.isAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
        // return UserDetails.super.isEnabled();
        return true;
    }
}
