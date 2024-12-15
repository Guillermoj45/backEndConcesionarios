package back.End.Concesionario.Service;

import back.End.Concesionario.DTO.BookingAddDTO;
import back.End.Concesionario.DTO.ReturnBookingDTO;
import back.End.Concesionario.DTO.ReturnVehicleDTO;
import back.End.Concesionario.Model.Booking;
import back.End.Concesionario.Model.User;
import back.End.Concesionario.Model.Vehicle;
import back.End.Concesionario.Repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final VehicleService vehicleService;
    private final User authenticatedUser;
    private final User AuthenticatedUser;

    public List<ReturnBookingDTO> getBookings() {

        List<Booking> bookings = bookingRepository.findBookingsByUserId(authenticatedUser.getId());
        List<ReturnBookingDTO> returnBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            returnBookings.add(new ReturnBookingDTO(booking));
        }
        return returnBookings;
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Booking with id " + id + " does not exist"));
    }

    public Booking addBooking(BookingAddDTO bookingAddDTO) {
        Booking booking = new Booking(bookingAddDTO);
        booking.setUser(new User());
        booking.getUser().setId(authenticatedUser.getId());
        booking.getUser().setRol(authenticatedUser.getRol());

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
