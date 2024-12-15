package back.End.Concesionario.Controller;

import back.End.Concesionario.DTO.BookingAddDTO;
import back.End.Concesionario.DTO.ReturnBookingDTO;
import back.End.Concesionario.Model.Booking;
import back.End.Concesionario.Service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/booking")
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping
    public List<ReturnBookingDTO> getBookings() {
        return bookingService.getBookings();
    }

    @GetMapping("{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping
    public Booking addBooking(@RequestBody BookingAddDTO booking) {
        return bookingService.addBooking(booking);
    }

    @PutMapping
    public Booking updateBooking(@RequestBody Booking booking) {
        return bookingService.updateBooking(booking);
    }

    @DeleteMapping("{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
