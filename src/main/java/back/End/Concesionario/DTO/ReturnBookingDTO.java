package back.End.Concesionario.DTO;

// Importación de clases necesarias
import back.End.Concesionario.Model.Booking; // Modelo Booking que representa una reserva
import back.End.Concesionario.Model.User; // Modelo User que representa un usuario
import lombok.Getter; // Genera automáticamente los métodos getter para los atributos
import lombok.Setter; // Genera automáticamente los métodos setter para los atributos

import java.time.LocalDate; // Importa la clase LocalDate para representar fechas

// Anotaciones Lombok para generar automáticamente los getters y setters
@Getter // Genera los métodos getter para los atributos de la clase
@Setter // Genera los métodos setter para los atributos de la clase
public class ReturnBookingDTO {
    // Atributos que representan los datos de la reserva para ser devueltos al cliente
    private Long id; // ID de la reserva
    private LocalDate dateBooking; // Fecha de la reserva
    private LocalDate dateDelivery; // Fecha de entrega del vehículo
    private User user; // Usuario que realizó la reserva
    private ReturnVehicleDTO vehicle; // Vehículo asociado a la reserva (utiliza un DTO para la representación del vehículo)

    // Constructor que inicializa el DTO con los valores de un objeto Booking
    public ReturnBookingDTO(Booking booking) {
        this.id = booking.getId(); // Asigna el ID de la reserva
        this.dateBooking = booking.getDateBooking(); // Asigna la fecha de la reserva
        this.dateDelivery = booking.getDateDelivery(); // Asigna la fecha de entrega
        this.user = booking.getUser(); // Asigna el usuario que hizo la reserva
        this.vehicle = new ReturnVehicleDTO(booking.getVehicle()); // Asigna el vehículo utilizando el DTO ReturnVehicleDTO
    }
}
