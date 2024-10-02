package galgo.com.backend.controllers;


import galgo.com.backend.dto.RestaurantTypeDTO;
import galgo.com.backend.services.IRestaurantTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("restaurants/types")
public class RestaurantTypeController {

    @Autowired
    private IRestaurantTypeService restaurantTypeService;


    @GetMapping
    public List<RestaurantTypeDTO> getAll(){
        return this.restaurantTypeService.findAll();
    }

    @GetMapping("/{actives}")
    public List<RestaurantTypeDTO> getActiveRestTypes() {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody RestaurantTypeDTO type, BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantTypeService.save(type));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody RestaurantTypeDTO restType, BindingResult result) {
        return  ResponseEntity.status(HttpStatus.OK).body(restaurantTypeService.updateById(id, restType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        boolean delete = restaurantTypeService.deleteById(id);
        if (delete) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Restaurant not found"));
    }

}
