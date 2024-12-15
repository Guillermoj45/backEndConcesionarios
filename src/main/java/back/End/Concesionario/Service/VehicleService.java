package back.End.Concesionario.Service;

import back.End.Concesionario.DTO.ReturnVehicleDTO;
import back.End.Concesionario.Model.Brand;
import back.End.Concesionario.Model.Vehicle;
import back.End.Concesionario.Repository.VehicleRepository;
import lombok.AllArgsConstructor;
import back.End.Concesionario.Model.Enum.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final BrandService brandService;

    public List<ReturnVehicleDTO> getVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<ReturnVehicleDTO> returnVehicleDTOs = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            ReturnVehicleDTO returnVehicleDTO = new ReturnVehicleDTO(vehicle);
            returnVehicleDTOs.add(returnVehicleDTO);
        }

        return returnVehicleDTOs;
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Vehicle with id " + id + " does not exist"));
    }

    public ReturnVehicleDTO getVehicleByIdReturn(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Vehicle with id " + id + " does not exist"));

        return new ReturnVehicleDTO(vehicle);
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public ReturnVehicleDTO updateVehicle(ReturnVehicleDTO vehicleDTO) {
        Brand brand= brandService.getBrandByName(vehicleDTO.getBrand());
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDTO.getId());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setStatus(Status.valueOf(vehicleDTO.getStatus()));
        vehicle.setBrand(brand);
        vehicle.setPrice(vehicleDTO.getPrice());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setImage(vehicleDTO.getImage());

        vehicle = vehicleRepository.save(vehicle);
        return new ReturnVehicleDTO(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public List<ReturnVehicleDTO> getVehiclesByBrand(Long brand) {
        List<Vehicle> vehicles = vehicleRepository.findByBrand(brand);
        List<ReturnVehicleDTO> returnVehicleDTOs = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            ReturnVehicleDTO returnVehicleDTO = new ReturnVehicleDTO(vehicle);
            returnVehicleDTOs.add(returnVehicleDTO);
        }

        return returnVehicleDTOs;
    }

    public List<ReturnVehicleDTO> getVehiclesByModel(String model) {
        List<Vehicle> vehicles = vehicleRepository.findByModel(model);
        List<ReturnVehicleDTO> returnVehicleDTOs = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            ReturnVehicleDTO returnVehicleDTO = new ReturnVehicleDTO(vehicle);
            returnVehicleDTOs.add(returnVehicleDTO);
        }

        return returnVehicleDTOs;
    }

    public List<ReturnVehicleDTO> getVehiclesByModelAndBrand(String modelName, Long numberBrand) {
        List<Vehicle> vehicles = vehicleRepository.findByModelAndBrand(modelName, numberBrand);
        List<ReturnVehicleDTO> returnVehicleDTOs = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            ReturnVehicleDTO returnVehicleDTO = new ReturnVehicleDTO(vehicle);
            returnVehicleDTOs.add(returnVehicleDTO);
        }

        return returnVehicleDTOs;
    }
}
