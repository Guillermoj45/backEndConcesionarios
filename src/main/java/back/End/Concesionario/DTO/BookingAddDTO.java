package back.End.Concesionario.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingAddDTO {
    private Long id;
    private String dateBooking;
    private String dateDelivery;
    private Long userId;
    private Long vehicleId;


}
