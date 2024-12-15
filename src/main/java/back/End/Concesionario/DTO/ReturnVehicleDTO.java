package back.End.Concesionario.DTO;

import back.End.Concesionario.Model.Vehicle;
import lombok.*;

import java.time.DateTimeException;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReturnVehicleDTO {
    Long id;
    String image;
    String brand;
    String model;
    Double price;
    String status;
    LocalDate year;

    public ReturnVehicleDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.image = vehicle.getImage();
        this.brand = vehicle.getBrand().getName();
        this.model = vehicle.getModel();
        this.price = vehicle.getPrice();
        this.status = vehicle.getStatus().toString();
        this.year = vehicle.getYear();
    }
}
