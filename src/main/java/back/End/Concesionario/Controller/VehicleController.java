package back.End.Concesionario.Controller;

import back.End.Concesionario.Model.Vehicle;
import back.End.Concesionario.Service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController("vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> getVehicle() {
        return vehicleService.getVehicles();
    }

    @GetMapping("{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping
    public Vehicle addVehicle(@PathVariable Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @PutMapping
    public Vehicle updateVehicle(@PathVariable Vehicle vehicle) {
        return vehicleService.updateVehicle(vehicle);
    }

    @DeleteMapping("{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}
