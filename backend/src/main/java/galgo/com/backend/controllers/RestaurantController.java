package galgo.com.backend.controllers;


import galgo.com.backend.dto.RestaurantDTO;
import galgo.com.backend.models.Restaurant;
import galgo.com.backend.services.IRestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = { "http://localhost:4200" })
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RestaurantDTO> getAll(){
        return this.restaurantService.findAll();
    };

    /**
     * find by id
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getOneById(@PathVariable Long restaurantId){
        Optional<RestaurantDTO> restaurant = this.restaurantService.findOneById(restaurantId);
        if (restaurant.isPresent()){
            return ResponseEntity.ok(restaurant.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Restaurant not found"));
    };


    /**
     * Save restaurant
     */
    @PostMapping
    public ResponseEntity<Restaurant> create(@RequestBody @Valid RestaurantDTO restaurant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.save(restaurant));
    }

    /**
     * Update by id
     */

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateOneById(@PathVariable Long restaurantId, @RequestBody @Valid RestaurantDTO restaurantDTO){
        Restaurant restaurant = restaurantService.updateOneById(restaurantDTO);
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.save(restaurantDTO));
    }

    @PutMapping("/{restaurantId/disabled}")
    public ResponseEntity<Restaurant> disableOneById(@PathVariable Long restaurantId){
        Restaurant restaurant = restaurantService.disableOneById(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }


}
