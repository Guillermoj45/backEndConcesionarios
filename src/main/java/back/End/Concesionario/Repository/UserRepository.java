package back.End.Concesionario.Repository;

// Importación de las clases necesarias
import back.End.Concesionario.Model.User; // Modelo User que representa un usuario
import org.springframework.data.jpa.repository.JpaRepository; // Interfaz para la implementación de operaciones CRUD con JPA
import org.springframework.stereotype.Repository; // Marca la clase como un repositorio

import java.util.Optional; // Importa la clase Optional para manejar valores que pueden estar ausentes

// Anotación que indica que esta interfaz es un repositorio de Spring
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Método para encontrar un usuario por su nombre de usuario, devolviendo un Optional
    Optional<User> findAllByUsername(String usuario);

    // Método para encontrar un usuario por su correo electrónico y contraseña, devolviendo un Optional
    Optional<User> findAllByEmailAndPassword(String email, String password);

    // Método para encontrar el primer usuario por su nombre de usuario, devolviendo un Optional
    Optional<User> findTopByUsername(String username);
}
