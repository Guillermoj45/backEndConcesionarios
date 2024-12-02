package back.End.Concesionario.Service;

import back.End.Concesionario.DTO.BookingAddDTO;
import back.End.Concesionario.Model.Booking;
import back.End.Concesionario.Model.User;
import back.End.Concesionario.Model.Vehicle;
import back.End.Concesionario.Repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final VehicleService vehicleService;

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Booking with id " + id + " does not exist"));
    }

    public Booking addBooking(BookingAddDTO bookingAddDTO) {
        Booking booking = new Booking(bookingAddDTO);
        User user = userService.getUserById(bookingAddDTO.getUserId());
        booking.setUser(user);

        Vehicle vehicle = vehicleService.getVehicleById(bookingAddDTO.getVehicleId());
        booking.setVehicle(vehicle);

        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
}
