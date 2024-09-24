package galgo.com.backend.controllers;


import galgo.com.backend.models.RestaurantType;
import galgo.com.backend.services.IRestaurantTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurants/types")
public class RestaurantTypeController {

    @Autowired
    private IRestaurantTypeService restaurantTypeService;


    @GetMapping
    public List<RestaurantType> getAll(){
        return this.restaurantTypeService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody RestaurantType type, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("TENEMOS ERRORES");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantTypeService.save(type));
    }
}
