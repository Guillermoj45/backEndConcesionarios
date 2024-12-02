package back.End.Concesionario.Model;


import back.End.Concesionario.DTO.BookingAddDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "date_booking")
    LocalDate dateBooking;

    @Column(name = "date_delivery")
    LocalDate dateDelivery;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    Vehicle vehicle;

    public Booking(BookingAddDTO bookingAddDTO) {
        this.dateBooking = LocalDate.parse(bookingAddDTO.getDateBooking());
        this.dateDelivery = LocalDate.parse(bookingAddDTO.getDateDelivery());
    }
}
