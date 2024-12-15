package back.End.Concesionario.Controller;

import back.End.Concesionario.DTO.ReturnVehicleDTO;
import back.End.Concesionario.Model.Vehicle;
import back.End.Concesionario.Service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController()
@RequestMapping("api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public List<ReturnVehicleDTO> getVehicle() {
        return vehicleService.getVehicles();
    }

    @GetMapping("brand/{brand}")
    public List<ReturnVehicleDTO> getVehicleByBrand(@PathVariable Long brand) {
        return vehicleService.getVehiclesByBrand(brand);
    }

    @GetMapping("model/{model}")
    public List<ReturnVehicleDTO> getVehicleByModel(@PathVariable String model) {
        return vehicleService.getVehiclesByModel(model);
    }

    @GetMapping("model/{modelName}/brand/{numberBrand}")
    public List<ReturnVehicleDTO> getVehicleByModelAndBrand(@PathVariable String modelName, @PathVariable Long numberBrand) {
        return vehicleService.getVehiclesByModelAndBrand(modelName, numberBrand);
    }

    @GetMapping("{id}")
    public ReturnVehicleDTO getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleByIdReturn(id);
    }

    @PostMapping
    public Vehicle addVehicle(@PathVariable Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @PutMapping
    public ReturnVehicleDTO updateVehicle(@RequestBody ReturnVehicleDTO vehicle) {
        return vehicleService.updateVehicle(vehicle);
    }

    @DeleteMapping("{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}
