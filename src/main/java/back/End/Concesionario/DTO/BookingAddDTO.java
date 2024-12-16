package back.End.Concesionario.DTO;

// Importaciones de Lombok para reducir código repetitivo (getters, setters, constructores, etc.)
import lombok.AllArgsConstructor; // Genera un constructor con todos los atributos de la clase
import lombok.Getter; // Genera automáticamente los métodos getter para todos los atributos
import lombok.NoArgsConstructor; // Genera un constructor sin argumentos
import lombok.Setter; // Genera automáticamente los métodos setter para todos los atributos

// Anotaciones Lombok para automatizar la creación de métodos y constructores
@Getter // Genera los métodos getter para todos los atributos
@Setter // Genera los métodos setter para todos los atributos
@AllArgsConstructor // Genera un constructor que incluye todos los atributos como parámetros
@NoArgsConstructor // Genera un constructor sin parámetros
public class BookingAddDTO {
    // Atributos del DTO (Data Transfer Object) utilizados para transferir datos relacionados con las reservas
    private Long id; // ID único de la reserva
    private String dateBooking; // Fecha de la reserva en formato de texto
    private String dateDelivery; // Fecha de entrega del vehículo en formato de texto
    private Long vehicleId; // ID del vehículo asociado a la reserva
}
