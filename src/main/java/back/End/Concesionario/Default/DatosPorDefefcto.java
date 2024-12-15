package back.End.Concesionario.Default;

import back.End.Concesionario.DTO.RegisterDTO;
import back.End.Concesionario.Model.Brand;
import back.End.Concesionario.Model.Enum.Rol;
import back.End.Concesionario.Model.Vehicle;
import back.End.Concesionario.Service.UserService;
import back.End.Concesionario.Service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Locale;

@Configuration
@AllArgsConstructor
public class DatosPorDefefcto {
    private final UserService userService;
    private final VehicleService vehicleService;


    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createDefaultUser() {
        return args -> {
            if (userService.findByUsername("admin") == null) {
                RegisterDTO defaultUser = new RegisterDTO();
                defaultUser.setUsername("admin");
                defaultUser.setPassword("123456");
                defaultUser.setRol(Rol.admin);
                userService.register(defaultUser);
            }
            if (userService.findByUsername("user") == null) {
                RegisterDTO defaultUser = new RegisterDTO();
                defaultUser.setUsername("user");
                defaultUser.setPassword("123456");
                defaultUser.setRol(Rol.client);
                userService.register(defaultUser);
            }
            if (vehicleService.getVehicles().isEmpty()){
                Brand bramd = new Brand();
                bramd.setName("Audi");
                Vehicle vehicle = new Vehicle();
                vehicle.setModel("A3");
                vehicle.setBrand(bramd);
                vehicle.setYear(LocalDate.of(2015, 1, 1));
                vehicle.setPrice(15000.0);
                vehicle.setImage("https://cdn-datak.motork.net/configurator-imgs/cars/es/original/AUDI/A3-SPORTBACK/44243_HATCHBACK-5-DOORS/audi-a3-sportback-front-view.jpg");

                vehicleService.addVehicle(vehicle);
            }
        };
    }
}
