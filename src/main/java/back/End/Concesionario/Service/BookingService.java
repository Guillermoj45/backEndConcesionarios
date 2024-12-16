package back.End.Concesionario.Service;

import back.End.Concesionario.DTO.BookingAddDTO; // Importa el DTO para agregar reservas
import back.End.Concesionario.DTO.ReturnBookingDTO; // Importa el DTO para devolver las reservas
import back.End.Concesionario.DTO.ReturnVehicleDTO; // Importa el DTO para devolver vehículos
import back.End.Concesionario.Model.Booking; // Importa la entidad de reserva
import back.End.Concesionario.Model.User; // Importa la entidad de usuario
import back.End.Concesionario.Model.Vehicle; // Importa la entidad de vehículo
import back.End.Concesionario.Repository.BookingRepository; // Importa el repositorio para las reservas
import lombok.AllArgsConstructor; // Inyecta automáticamente el constructor con las dependencias necesarias
import org.springframework.stereotype.Service; // Marca la clase como servicio de Spring

import java.util.ArrayList; // Importa la clase ArrayList para almacenar las reservas
import java.util.List; // Importa la interfaz List

@Service // Indica que esta clase es un servicio que manejará la lógica de negocio
@AllArgsConstructor // Genera un constructor con todos los parámetros necesarios
public class BookingService {

    private final BookingRepository bookingRepository; // Repositorio para interactuar con la base de datos de reservas
    private final UserService userService; // Servicio para gestionar usuarios
    private final VehicleService vehicleService; // Servicio para gestionar vehículos
    private final User authenticatedUser; // Usuario autenticado actualmente
    private final User AuthenticatedUser; // Otra referencia al usuario autenticado (parece redundante y puede ser eliminada)

    // Obtiene todas las reservas del usuario autenticado
    public List<ReturnBookingDTO> getBookings() {
        List<Booking> bookings = bookingRepository.findBookingsByUserId(authenticatedUser.getId()); // Obtiene las reservas por usuario
        List<ReturnBookingDTO> returnBookings = new ArrayList<>(); // Crea una lista para devolver las reservas como DTO

        // Convierte las reservas en DTOs y las añade a la lista
        for (Booking booking : bookings) {
            returnBookings.add(new ReturnBookingDTO(booking));
        }
        return returnBookings; // Devuelve la lista de reservas
    }

    // Obtiene una reserva por su ID
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id) // Busca la reserva por ID
                .orElseThrow(() -> new IllegalStateException("Booking with id " + id + " does not exist")); // Lanza una excepción si no se encuentra
    }

    // Agrega una nueva reserva
    public Booking addBooking(BookingAddDTO bookingAddDTO) {
        Booking booking = new Booking(bookingAddDTO); // Crea una reserva a partir del DTO recibido
        booking.setUser(new User()); // Asocia un usuario a la reserva
        booking.getUser().setId(authenticatedUser.getId()); // Establece el ID del usuario autenticado
        booking.getUser().setRol(authenticatedUser.getRol()); // Establece el rol del usuario autenticado

        Vehicle vehicle = vehicleService.getVehicleById(bookingAddDTO.getVehicleId()); // Obtiene el vehículo a partir del ID recibido
        booking.setVehicle(vehicle); // Establece el vehículo en la reserva

        return bookingRepository.save(booking); // Guarda la nueva reserva en la base de datos
    }

    // Elimina una reserva por su ID
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id); // Elimina la reserva de la base de datos
    }

    // Actualiza una reserva existente
    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking); // Guarda los cambios de la reserva en la base de datos
    }
}
