package back.End.Concesionario.Repository;

// Importación de las clases necesarias
import back.End.Concesionario.Model.Booking; // Modelo Booking que representa una reserva
import org.springframework.data.jpa.repository.JpaRepository; // Interfaz para la implementación de operaciones CRUD con JPA
import org.springframework.data.jpa.repository.Query; // Anotación para definir consultas personalizadas
import org.springframework.stereotype.Repository; // Marca la clase como un repositorio

import java.util.List; // Importa la clase List para manejar colecciones de objetos

// Anotación que indica que esta interfaz es un repositorio de Spring
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Consulta personalizada para encontrar reservas por el ID del usuario
    @Query("SELECT b FROM Booking b WHERE b.user.id = ?1")
    List<Booking> findBookingsByUserId(Long userId); // Devuelve una lista de reservas asociadas a un usuario
}
