package back.End.Concesionario.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.DateTimeException;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ReturnVehicleDTO {
    Long id;
    String image;
    String brand;
    String model;
    Double price;
    String status;
    LocalDate year;
}
