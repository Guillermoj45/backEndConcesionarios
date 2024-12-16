package back.End.Concesionario.Controller;

// Importaciones necesarias para el controlador
import back.End.Concesionario.Model.Brand; // Modelo de la entidad Brand (marca)
import back.End.Concesionario.Service.BrandService; // Servicio para manejar la lógica de negocio de Brand
import lombok.AllArgsConstructor; // Anotación para generar un constructor con todas las propiedades
import org.springframework.web.bind.annotation.*; // Anotaciones para el manejo de solicitudes HTTP

import java.util.List; // Importación para manejar listas

// Define la clase como un controlador REST para manejar solicitudes HTTP relacionadas con las marcas
@RestController
// Asigna un prefijo base "api/brand" a todas las rutas de este controlador
@RequestMapping("api/brand")
// Genera automáticamente un constructor con todas las propiedades finales
@AllArgsConstructor
public class BrandController {

    // Dependencia del servicio que contiene la lógica de negocio para las marcas
    private final BrandService brandService;

    // Maneja solicitudes GET a "api/brand" para devolver una lista de todas las marcas
    @GetMapping
    public List<Brand> getBrands() {
        // Llama al servicio para obtener todas las marcas y las devuelve como una lista
        return brandService.getBrands();
    }

    // Maneja solicitudes GET a "api/brand/{id}" para obtener una marca específica por su ID
    @GetMapping("{id}")
    public Brand getBrandById(@PathVariable Long id) {
        // Llama al servicio para buscar la marca por ID y la devuelve
        return brandService.getBrandById(id);
    }

    // Maneja solicitudes PUT a "api/brand" para actualizar una marca existente
    @PutMapping
    public Brand updateBrand(@RequestBody Brand brand) {
        // Llama al servicio para actualizar los datos de la marca y devuelve el objeto actualizado
        return brandService.updateBrand(brand);
    }

    // Maneja solicitudes POST a "api/brand" para agregar una nueva marca
    @PostMapping
    public Brand addBrand(@RequestBody Brand brand) {
        // Llama al servicio para agregar una nueva marca con los datos recibidos en el cuerpo de la solicitud
        return brandService.addBrand(brand);
    }

    // Maneja solicitudes DELETE a "api/brand/{id}" para eliminar una marca por su ID
    @DeleteMapping("{id}")
    public void deleteBrand(@PathVariable Long id) {
        // Llama al servicio para eliminar la marca correspondiente al ID proporcionado
        brandService.deleteBrand(id);
    }
}
