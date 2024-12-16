package back.End.Concesionario.Service;

import back.End.Concesionario.Model.Brand; // Importa la entidad Brand (Marca)
import back.End.Concesionario.Repository.BrandRepository; // Importa el repositorio para las marcas
import lombok.AllArgsConstructor; // Anota la clase para generar un constructor con todas las dependencias
import org.springframework.stereotype.Service; // Marca la clase como un servicio de Spring

import java.util.List; // Importa la interfaz List

@Service // Indica que esta clase es un servicio que gestionará la lógica de negocio relacionada con las marcas
@AllArgsConstructor // Genera un constructor con las dependencias necesarias para inyectarlas automáticamente
public class BrandService {
    private final BrandRepository brandRepository; // Repositorio para interactuar con la base de datos de marcas

    // Obtiene todas las marcas
    public List<Brand> getBrands() {
        return brandRepository.findAll(); // Devuelve todas las marcas almacenadas en la base de datos
    }

    // Obtiene una marca por su ID
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id) // Busca la marca por ID
                .orElseThrow(() -> new IllegalStateException("Brand with id " + id + " does not exist")); // Lanza excepción si no existe
    }

    // Agrega una nueva marca
    public Brand addBrand(Brand brand) {
        return brandRepository.save(brand); // Guarda la nueva marca en la base de datos
    }

    // Elimina una marca por su ID
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id); // Elimina la marca de la base de datos por ID
    }

    // Actualiza una marca existente
    public Brand updateBrand(Brand brand) {
        return brandRepository.save(brand); // Guarda los cambios de la marca en la base de datos
    }

    // Obtiene una marca por su nombre
    public Brand getBrandByName(String brand) {
        return brandRepository.findBrandByName(brand) // Busca la marca por nombre
                .orElseThrow(() -> new IllegalStateException("Brand with name " + brand + " does not exist")); // Lanza excepción si no existe
    }
}
