package back.End.Concesionario.Repository;

import back.End.Concesionario.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v WHERE v.brand.id = ?1")
    List<Vehicle> findByBrand(Long brand);

    @Query("SELECT v FROM Vehicle v WHERE v.model LIKE %:model%")
    List<Vehicle> findByModel(String model);

    @Query("SELECT v FROM Vehicle v WHERE v.model LIKE %:modelName% AND v.brand.id = :numberBrand")
    List<Vehicle> findByModelAndBrand(String modelName, Long numberBrand);
}
