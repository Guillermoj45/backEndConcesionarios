package back.End.Concesionario.Service;

import back.End.Concesionario.DTO.ReturnVehicleDTO;
import back.End.Concesionario.Model.Vehicle;
import back.End.Concesionario.Repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public List<ReturnVehicleDTO> getVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<ReturnVehicleDTO> returnVehicleDTOs = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            ReturnVehicleDTO returnVehicleDTO = new ReturnVehicleDTO();
            returnVehicleDTO.setId(vehicle.getId());
            returnVehicleDTO.setImage(vehicle.getImage());
            returnVehicleDTO.setBrand(vehicle.getBrand().getName());
            returnVehicleDTO.setModel(vehicle.getModel());
            returnVehicleDTO.setPrice(vehicle.getPrice());
            returnVehicleDTO.setStatus(vehicle.getStatus().toString());
            returnVehicleDTO.setYear(vehicle.getYear());
            returnVehicleDTOs.add(returnVehicleDTO);
        }

        return returnVehicleDTOs;
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Vehicle with id " + id + " does not exist"));
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
