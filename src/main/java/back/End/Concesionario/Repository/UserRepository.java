package back.End.Concesionario.Repository;

import back.End.Concesionario.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findAllByUsername(String usuario);
    Optional<User> findAllByEmailAndPassword(String email, String password);
    Optional<User> findTopByUsername(String username);
}
