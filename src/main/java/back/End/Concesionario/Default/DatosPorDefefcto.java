package back.End.Concesionario.Default;

// Importaciones necesarias para DTOs, modelos, servicios y configuraciones
import back.End.Concesionario.DTO.RegisterDTO; // DTO para el registro de usuarios
import back.End.Concesionario.Model.Brand; // Modelo de la marca de vehículos
import back.End.Concesionario.Model.Enum.Rol; // Enumeración para los roles de usuario (admin, client)
import back.End.Concesionario.Model.Enum.Status; // Enumeración para el estado del vehículo (NEW, USED, etc.)
import back.End.Concesionario.Model.Vehicle; // Modelo de vehículos
import back.End.Concesionario.Service.BrandService; // Servicio para gestionar las marcas
import back.End.Concesionario.Service.UserService; // Servicio para gestionar los usuarios
import back.End.Concesionario.Service.VehicleService; // Servicio para gestionar los vehículos
import lombok.AllArgsConstructor; // Genera un constructor con todas las propiedades finales de la clase
import org.springframework.boot.CommandLineRunner; // Interfaz para ejecutar código al iniciar la aplicación
import org.springframework.context.annotation.Bean; // Marca un método como proveedor de un bean de Spring
import org.springframework.context.annotation.Configuration; // Marca esta clase como configuración de Spring
import org.springframework.security.crypto.password.PasswordEncoder; // Servicio para encriptar contraseñas

import java.time.LocalDate; // Clase para manejar fechas

// Marca esta clase como una configuración de Spring
@Configuration
// Genera un constructor con todas las dependencias necesarias
@AllArgsConstructor
public class DatosPorDefefcto {

    // Inyección de dependencias necesarias para usuarios, vehículos y marcas
    private final UserService userService;
    private final VehicleService vehicleService;
    private final BrandService brandService;

    // Inyección del servicio para encriptar contraseñas
    private PasswordEncoder passwordEncoder;

    // Define un bean que se ejecuta al iniciar la aplicación para crear datos por defecto
    @Bean
    public CommandLineRunner createDefaultUser() {
        return args -> {
            // Comprueba si no existe un usuario con nombre "admin" y lo crea con rol administrador
            if (userService.findByUsername("admin") == null) {
                RegisterDTO defaultUser = new RegisterDTO();
                defaultUser.setUsername("admin");
                defaultUser.setPassword("123456"); // Contraseña predeterminada
                defaultUser.setRol(Rol.admin); // Rol de administrador
                userService.register(defaultUser); // Registra el usuario en el sistema
            }

            // Comprueba si no existe un usuario con nombre "user" y lo crea con rol cliente
            if (userService.findByUsername("user") == null) {
                RegisterDTO defaultUser = new RegisterDTO();
                defaultUser.setUsername("user");
                defaultUser.setPassword("123456"); // Contraseña predeterminada
                defaultUser.setRol(Rol.client); // Rol de cliente
                userService.register(defaultUser); // Registra el usuario en el sistema
            }

            // Comprueba si no existen vehículos registrados y, de ser así, crea uno por defecto
            if (vehicleService.getVehicles().isEmpty()) {
                // Crea una nueva marca de vehículo ("Audi") y la guarda en el sistema
                Brand brand = new Brand();
                brand.setName("Audi");
                brand = brandService.addBrand(brand);

                // Crea un vehículo asociado a la marca "Audi" con características predeterminadas
                Vehicle vehicle = new Vehicle();
                vehicle.setModel("A3"); // Modelo del vehículo
                vehicle.setBrand(brand); // Marca asociada al vehículo
                vehicle.setYear(LocalDate.of(2015, 1, 1)); // Año de fabricación
                vehicle.setPrice(15000.0); // Precio del vehículo
                vehicle.setStatus(Status.NEW); // Estado del vehículo (nuevo)
                vehicle.setImage("https://cdn-datak.motork.net/configurator-imgs/cars/es/original/AUDI/A3-SPORTBACK/44243_HATCHBACK-5-DOORS/audi-a3-sportback-front-view.jpg"); // URL de la imagen del vehículo

                // Añade el vehículo al sistema
                vehicleService.addVehicle(vehicle);
            }
        };
    }
}
