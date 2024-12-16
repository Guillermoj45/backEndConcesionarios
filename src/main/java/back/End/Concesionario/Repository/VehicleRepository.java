package back.End.Concesionario.Repository;

// Importación de las clases necesarias
import back.End.Concesionario.Model.Vehicle; // Modelo Vehicle que representa un vehículo
import org.springframework.data.jpa.repository.JpaRepository; // Interfaz para la implementación de operaciones CRUD con JPA
import org.springframework.data.jpa.repository.Query; // Anotación para definir consultas personalizadas
import org.springframework.stereotype.Repository; // Marca la clase como un repositorio

import java.util.List; // Importa la clase List para manejar colecciones de objetos

// Anotación que indica que esta interfaz es un repositorio de Spring
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // Consulta personalizada para encontrar vehículos por la ID de la marca
    @Query("SELECT v FROM Vehicle v WHERE v.brand.id = ?1")
    List<Vehicle> findByBrand(String brand);

    // Consulta personalizada para encontrar vehículos por la ID de la marca (con tipo Long para la ID)
    @Query("SELECT v FROM Vehicle v WHERE v.brand.id = ?1")
    List<Vehicle> findByBrand(Long brand);

    // Consulta personalizada para encontrar vehículos cuyo modelo contenga una cadena específica
    @Query("SELECT v FROM Vehicle v WHERE v.model LIKE %:model%")
    List<Vehicle> findByModel(String model);

    // Consulta personalizada para encontrar vehículos por modelo y marca
    @Query("SELECT v FROM Vehicle v WHERE v.model LIKE %:modelName% AND v.brand.id = :numberBrand")
    List<Vehicle> findByModelAndBrand(String modelName, Long numberBrand);

    // Consulta personalizada para encontrar vehículos cuyo precio sea menor o igual a un valor máximo
    @Query("SELECT v FROM Vehicle v WHERE v.price <= :maxPrice")
    List<Vehicle> findByPriceRange(Double maxPrice);
}
