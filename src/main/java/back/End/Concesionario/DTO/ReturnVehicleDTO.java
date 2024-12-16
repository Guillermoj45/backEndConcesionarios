package back.End.Concesionario.DTO;

// Importación de la clase Vehicle y las anotaciones de Lombok
import back.End.Concesionario.Model.Vehicle; // Importa el modelo Vehicle
import lombok.*; // Anotaciones Lombok para generar código automáticamente

import java.time.DateTimeException; // Importa para manejar excepciones de tiempo (aunque no se utiliza en este código)
import java.time.LocalDate; // Importa la clase LocalDate para manejar fechas

// Anotaciones de Lombok para reducir el código repetitivo
@Getter // Genera los métodos getter para los atributos de la clase
@Setter // Genera los métodos setter para los atributos de la clase
@ToString // Genera automáticamente el método toString() para la clase
@AllArgsConstructor // Genera un constructor con todos los parámetros
@NoArgsConstructor // Genera un constructor sin parámetros
public class ReturnVehicleDTO {
    // Atributos que representan los datos del vehículo que se devolverán al cliente
    Long id; // ID del vehículo
    String image; // URL de la imagen del vehículo
    String brand; // Marca del vehículo
    String model; // Modelo del vehículo
    Double price; // Precio del vehículo
    String status; // Estado del vehículo (nuevo, usado, etc.)
    LocalDate year; // Año de fabricación del vehículo

    // Constructor que inicializa el DTO a partir de un objeto Vehicle
    public ReturnVehicleDTO(Vehicle vehicle) {
        this.id = vehicle.getId(); // Asigna el ID del vehículo
        this.image = vehicle.getImage(); // Asigna la imagen del vehículo
        this.brand = vehicle.getBrand().getName(); // Asigna el nombre de la marca del vehículo
        this.model = vehicle.getModel(); // Asigna el modelo del vehículo
        this.price = vehicle.getPrice(); // Asigna el precio del vehículo
        this.status = vehicle.getStatus().toString(); // Asigna el estado del vehículo
        this.year = vehicle.getYear(); // Asigna el año del vehículo
    }
}
