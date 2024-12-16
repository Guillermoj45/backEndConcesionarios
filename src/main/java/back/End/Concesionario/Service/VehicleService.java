package back.End.Concesionario.Service;

import back.End.Concesionario.DTO.ReturnVehicleDTO; // Importa el DTO para devolver la información de los vehículos
import back.End.Concesionario.Model.Brand; // Importa la entidad Brand (Marca)
import back.End.Concesionario.Model.Vehicle; // Importa la entidad Vehicle (Vehículo)
import back.End.Concesionario.Repository.VehicleRepository; // Importa el repositorio para interactuar con la base de datos de vehículos
import lombok.AllArgsConstructor; // Anota la clase para generar un constructor con todas las dependencias
import back.End.Concesionario.Model.Enum.Status; // Importa el Enum que define el estado de los vehículos
import org.springframework.stereotype.Service; // Marca la clase como un servicio de Spring

import java.util.ArrayList; // Importa la clase ArrayList para manejar colecciones de vehículos
import java.util.List; // Importa la interfaz List para trabajar con colecciones

@Service // Indica que esta clase es un servicio que gestionará la lógica de negocio relacionada con los vehículos
@AllArgsConstructor // Genera un constructor con las dependencias necesarias para inyectarlas automáticamente
public class VehicleService {
    private final VehicleRepository vehicleRepository; // Repositorio para interactuar con la base de datos de vehículos
    private final BrandService brandService; // Servicio para manejar las marcas de los vehículos

    // Obtiene todos los vehículos y los convierte en DTOs para su retorno
    public List<ReturnVehicleDTO> getVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll(); // Obtiene todos los vehículos
        List<ReturnVehicleDTO> returnVehicleDTOs = new ArrayList<>(); // Lista para almacenar los DTOs de vehículos

        for (Vehicle vehicle : vehicles) {
            ReturnVehicleDTO returnVehicleDTO = new ReturnVehicleDTO(vehicle); // Convierte el vehículo en DTO
            returnVehicleDTOs.add(returnVehicleDTO); // Añade el DTO a la lista
        }

        return returnVehicleDTOs; // Devuelve la lista de vehículos en formato DTO
    }

    // Obtiene un vehículo por su ID
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id) // Busca el vehículo por su ID
                .orElseThrow(() -> new IllegalStateException("Vehicle with id " + id + " does not exist")); // Lanza excepción si no existe
    }

    // Obtiene un vehículo por su ID y lo convierte en DTO
    public ReturnVehicleDTO getVehicleByIdReturn(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id) // Busca el vehículo por su ID
                .orElseThrow(() -> new IllegalStateException("Vehicle with id " + id + " does not exist"));

        return new ReturnVehicleDTO(vehicle); // Devuelve el vehículo como un DTO
    }

    // Añade un nuevo vehículo a la base de datos
    public Vehicle addVehicle(Vehicle vehicle) {
        Brand brand = vehicle.getBrand(); // Obtiene la marca del vehículo
        if (brand.getId() == null) { // Si la marca no tiene ID, se guarda en la base de datos
            brand = brandService.addBrand(brand); // Añade la marca
            vehicle.setBrand(brand); // Asocia la marca al vehículo
        }

        return vehicleRepository.save(vehicle); // Guarda el vehículo en la base de datos y lo devuelve
    }

    // Actualiza un vehículo con la información del DTO proporcionado
    public ReturnVehicleDTO updateVehicle(ReturnVehicleDTO vehicleDTO) {
        Brand brand = brandService.getBrandByName(vehicleDTO.getBrand()); // Obtiene la marca por nombre
        Vehicle vehicle = new Vehicle(); // Crea un nuevo objeto Vehicle
        vehicle.setId(vehicleDTO.getId()); // Establece el ID del vehículo
        vehicle.setModel(vehicleDTO.getModel()); // Establece el modelo del vehículo
        vehicle.setStatus(Status.valueOf(vehicleDTO.getStatus())); // Establece el estado del vehículo
        vehicle.setBrand(brand); // Establece la marca del vehículo
        vehicle.setPrice(vehicleDTO.getPrice()); // Establece el precio del vehículo
        vehicle.setYear(vehicleDTO.getYear()); // Establece el año del vehículo
        vehicle.setImage(vehicleDTO.getImage()); // Establece la imagen del vehículo

        vehicle = vehicleRepository.save(vehicle); // Guarda el vehículo actualizado en la base de datos
        return new ReturnVehicleDTO(vehicle); // Devuelve el vehículo actualizado como DTO
    }

    // Elimina un vehículo por su ID
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id); // Elimina el vehículo de la base de datos por su ID
    }

    // Obtiene vehículos por marca
    public List<ReturnVehicleDTO> getVehiclesByBrand(Long brand) {
        List<Vehicle> vehicles = vehicleRepository.findByBrand(brand); // Busca vehículos por marca
        List<ReturnVehicleDTO> returnVehicleDTOs = new ArrayList<>(); // Lista para almacenar los DTOs de vehículos

        for (Vehicle vehicle : vehicles) {
            ReturnVehicleDTO returnVehicleDTO = new ReturnVehicleDTO(vehicle); // Convierte cada vehículo en DTO
            returnVehicleDTOs.add(returnVehicleDTO); // Añade el DTO a la lista
        }

        return returnVehicleDTOs; // Devuelve la lista de vehículos por marca en formato DTO
    }

    // Obtiene vehículos por modelo
    public List<ReturnVehicleDTO> getVehiclesByModel(String model) {
        List<Vehicle> vehicles = vehicleRepository.findByModel(model); // Busca vehículos por modelo
        List<ReturnVehicleDTO> returnVehicleDTOs = new ArrayList<>(); // Lista para almacenar los DTOs de vehículos

        for (Vehicle vehicle : vehicles) {
            ReturnVehicleDTO returnVehicleDTO = new ReturnVehicleDTO(vehicle); // Convierte cada vehículo en DTO
            returnVehicleDTOs.add(returnVehicleDTO); // Añade el DTO a la lista
        }

        return returnVehicleDTOs; // Devuelve la lista de vehículos por modelo en formato DTO
    }

    // Obtiene vehículos por modelo y marca
    public List<ReturnVehicleDTO> getVehiclesByModelAndBrand(String modelName, Long numberBrand) {
        List<Vehicle> vehicles = vehicleRepository.findByModelAndBrand(modelName, numberBrand); // Busca vehículos por modelo y marca
        List<ReturnVehicleDTO> returnVehicleDTOs = new ArrayList<>(); // Lista para almacenar los DTOs de vehículos

        for (Vehicle vehicle : vehicles) {
            ReturnVehicleDTO returnVehicleDTO = new ReturnVehicleDTO(vehicle); // Convierte cada vehículo en DTO
            returnVehicleDTOs.add(returnVehicleDTO); // Añade el DTO a la lista
        }

        return returnVehicleDTOs; // Devuelve la lista de vehículos por modelo y marca en formato DTO
    }

    // Obtiene vehículos por nombre (modelo)
    public List<Vehicle> getVehiclesByName(String name) {
        return vehicleRepository.findByModel(name); // Busca vehículos por nombre de modelo
    }

    // Obtiene vehículos por marca (nombre)
    public List<Vehicle> getVehiclesByBrand(String brand) {
        return vehicleRepository.findByBrand(brand); // Busca vehículos por nombre de marca
    }

    // Obtiene vehículos dentro de un rango de precio
    public List<Vehicle> getVehiclesByPriceRange(Double maxPrice) {
        return vehicleRepository.findByPriceRange(maxPrice); // Busca vehículos cuyo precio sea menor o igual al máximo especificado
    }
}
