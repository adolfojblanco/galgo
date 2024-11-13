package galgo.com.backend.controllers;


import galgo.com.backend.dto.ProductDTO;
import galgo.com.backend.dto.ResponseRecord;
import galgo.com.backend.dto.RestaurantDTO;
import galgo.com.backend.dto.UserDTO;
import galgo.com.backend.mappers.ProductMapper;
import galgo.com.backend.mappers.RestaurantMapper;
import galgo.com.backend.models.Restaurant;
import galgo.com.backend.services.IProductService;
import galgo.com.backend.services.IRestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IRestaurantService restaurantService;


    @GetMapping("/{restaurantId}")
    public ResponseEntity<ResponseRecord> findAll(@PathVariable Long restaurantId) {
        List< ProductDTO> products = this.productService.findAll();
        return ResponseEntity.ok(ResponseRecord.builder().data(products).build());
    }

    @PostMapping("/{restaurantId}/new-product")
    public ResponseEntity<ResponseRecord> newProductFromRestaurant(@PathVariable Long restaurantId, @Valid @RequestBody ProductDTO productDTO) {
        Optional<RestaurantDTO> restaurantDTO = this.restaurantService.findOneById(restaurantId);
        if (restaurantDTO.isPresent()) {
            ProductDTO newProduct = this.productService.save(restaurantDTO.get(), productDTO);
            return ResponseEntity.ok(ResponseRecord.builder().data(newProduct).build());
        }
        return ResponseEntity.notFound().build();
    }
}
