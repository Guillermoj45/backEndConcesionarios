package back.End.Concesionario.Repository;

// Importación de las clases necesarias
import back.End.Concesionario.Model.Brand; // Modelo Brand que representa una marca
import org.springframework.data.jpa.repository.JpaRepository; // Interfaz para la implementación de operaciones CRUD con JPA
import org.springframework.stereotype.Repository; // Marca la clase como un repositorio

import java.util.Optional; // Importa la clase Optional para manejar valores que pueden estar ausentes

// Anotación que indica que esta interfaz es un repositorio de Spring
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    // Método que encuentra una marca por su nombre y devuelve un Optional
    Optional<Brand> findByName(String name);

    // Método adicional para encontrar una marca por nombre, también devuelve un Optional
    Optional<Brand> findBrandByName(String brand);
}
