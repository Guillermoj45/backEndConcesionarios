package back.End.Concesionario.Controller;


import back.End.Concesionario.Model.Brand;
import back.End.Concesionario.Service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
@AllArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public List<Brand> getBrands() {
        return brandService.getBrands();
    }

    @GetMapping("{id}")
    public Brand getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id);
    }

    @PutMapping
    public Brand updateBrand(@RequestBody Brand brand) {
        return brandService.updateBrand(brand);
    }

    @PostMapping
    public Brand addBrand(@RequestBody Brand brand) {
        return brandService.addBrand(brand);
    }

    @DeleteMapping("{id}")
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
    }


}
