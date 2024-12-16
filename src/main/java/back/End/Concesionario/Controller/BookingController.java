package back.End.Concesionario.Controller;

// Importaciones necesarias para DTOs, servicios y anotaciones de Spring
import back.End.Concesionario.DTO.BookingAddDTO;
import back.End.Concesionario.DTO.ReturnBookingDTO;
import back.End.Concesionario.Model.Booking;
import back.End.Concesionario.Service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Marca la clase como un controlador REST que gestiona las solicitudes HTTP
@RestController
// Define la ruta base para todas las solicitudes manejadas por este controlador
@RequestMapping("api/booking")
// Genera un constructor con todas las propiedades declaradas en la clase
@AllArgsConstructor
public class BookingController {
    // Dependencia de BookingService inyectada mediante constructor
    private final BookingService bookingService;

    // Maneja solicitudes GET a la ruta "api/booking" para obtener una lista de reservas
    @GetMapping
    public List<ReturnBookingDTO> getBookings() {
        // Devuelve una lista de objetos ReturnBookingDTO obtenidos del servicio
        return bookingService.getBookings();
    }

    // Maneja solicitudes GET a "api/booking/{id}" para obtener una reserva por ID
    @GetMapping("{id}")
    public Booking getBookingById(@PathVariable Long id) {
        // Utiliza el servicio para obtener y devolver la reserva correspondiente al ID
        return bookingService.getBookingById(id);
    }

    // Maneja solicitudes POST a "api/booking" para agregar una nueva reserva
    @PostMapping
    public Booking addBooking(@RequestBody BookingAddDTO booking) {
        // Llama al servicio para añadir una nueva reserva basada en los datos recibidos
        return bookingService.addBooking(booking);
    }

    // Maneja solicitudes PUT a "api/booking" para actualizar una reserva existente
    @PutMapping
    public Booking updateBooking(@RequestBody Booking booking) {
        // Llama al servicio para actualizar la reserva y devuelve el objeto actualizado
        return bookingService.updateBooking(booking);
    }

    // Maneja solicitudes DELETE a "api/booking/{id}" para eliminar una reserva por ID
    @DeleteMapping("{id}")
    public void deleteBooking(@PathVariable Long id) {
        // Llama al servicio para eliminar la reserva correspondiente al ID
        bookingService.deleteBooking(id);
    }
}
