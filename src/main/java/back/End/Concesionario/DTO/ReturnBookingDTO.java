package back.End.Concesionario.DTO;

import back.End.Concesionario.Model.Booking;
import back.End.Concesionario.Model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReturnBookingDTO {
    private Long id;
    private LocalDate dateBooking;
    private LocalDate dateDelivery;
    private User user;
    private ReturnVehicleDTO vehicle;

    public ReturnBookingDTO (Booking booking) {
        this.id = booking.getId();
        this.dateBooking = booking.getDateBooking();
        this.dateDelivery = booking.getDateDelivery();
        this.user = booking.getUser();
        this.vehicle = new ReturnVehicleDTO(booking.getVehicle());
    }
}
