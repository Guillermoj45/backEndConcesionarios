package back.End.Concesionario.Controller;

// Importaciones necesarias para DTOs, modelo y servicio
import back.End.Concesionario.DTO.ReturnVehicleDTO; // DTO para devolver información de los vehículos
import back.End.Concesionario.Model.Vehicle; // Modelo de la entidad Vehicle (vehículo)
import back.End.Concesionario.Service.VehicleService; // Servicio que contiene la lógica de negocio de los vehículos
import lombok.AllArgsConstructor; // Anotación para generar un constructor con todas las propiedades
import org.springframework.web.bind.annotation.*; // Anotaciones para manejar solicitudes HTTP

import java.util.List; // Importación para manejar listas

// Marca la clase como un controlador REST que gestiona solicitudes relacionadas con vehículos
@AllArgsConstructor // Genera un constructor con todas las propiedades finales de la clase
@RestController
@RequestMapping("api/vehicle") // Ruta base para todas las solicitudes de este controlador
public class VehicleController {

    // Dependencia del servicio que contiene la lógica de negocio para vehículos
    private final VehicleService vehicleService;

    // Maneja solicitudes GET a "api/vehicle" para obtener una lista de vehículos
    @GetMapping
    public List<ReturnVehicleDTO> getVehicle() {
        // Llama al servicio para obtener todos los vehículos y devuelve una lista de DTOs
        return vehicleService.getVehicles();
    }

    // Maneja solicitudes GET a "api/vehicle/brand/{brand}" para obtener vehículos por su marca
    @GetMapping("brand/{brand}")
    public List<ReturnVehicleDTO> getVehicleByBrand(@PathVariable Long brand) {
        // Llama al servicio para obtener vehículos que coincidan con la marca proporcionada
        return vehicleService.getVehiclesByBrand(brand);
    }

    // Maneja solicitudes GET a "api/vehicle/model/{model}" para obtener vehículos por su modelo
    @GetMapping("model/{model}")
    public List<ReturnVehicleDTO> getVehicleByModel(@PathVariable String model) {
        // Llama al servicio para obtener vehículos que coincidan con el modelo proporcionado
        return vehicleService.getVehiclesByModel(model);
    }

    // Maneja solicitudes GET a "api/vehicle/model/{modelName}/brand/{numberBrand}"
    // para obtener vehículos que coincidan con un modelo y una marca específicos
    @GetMapping("model/{modelName}/brand/{numberBrand}")
    public List<ReturnVehicleDTO> getVehicleByModelAndBrand(@PathVariable String modelName, @PathVariable Long numberBrand) {
        // Llama al servicio para buscar vehículos que coincidan con el modelo y la marca
        return vehicleService.getVehiclesByModelAndBrand(modelName, numberBrand);
    }

    // Maneja solicitudes GET a "api/vehicle/{id}" para obtener un vehículo específico por su ID
    @GetMapping("{id}")
    public ReturnVehicleDTO getVehicleById(@PathVariable Long id) {
        // Llama al servicio para buscar y devolver un vehículo por su ID
        return vehicleService.getVehicleByIdReturn(id);
    }

    // Maneja solicitudes POST a "api/vehicle" para agregar un nuevo vehículo
    @PostMapping
    public Vehicle addVehicle(@PathVariable Vehicle vehicle) {
        // Llama al servicio para añadir un nuevo vehículo con los datos proporcionados
        return vehicleService.addVehicle(vehicle);
    }

    // Maneja solicitudes PUT a "api/vehicle" para actualizar un vehículo existente
    @PutMapping
    public ReturnVehicleDTO updateVehicle(@RequestBody ReturnVehicleDTO vehicle) {
        // Llama al servicio para actualizar los datos del vehículo y devuelve el vehículo actualizado
        return vehicleService.updateVehicle(vehicle);
    }

    // Maneja solicitudes DELETE a "api/vehicle/{id}" para eliminar un vehículo por su ID
    @DeleteMapping("{id}")
    public void deleteVehicle(@PathVariable Long id) {
        // Llama al servicio para eliminar el vehículo correspondiente al ID proporcionado
        vehicleService.deleteVehicle(id);
    }
}
