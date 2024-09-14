package galgo.com.backend.controllers;


import galgo.com.backend.dto.RestaurantDTO;
import galgo.com.backend.models.Restaurant;
import galgo.com.backend.services.IRestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getAll(){
        return this.restaurantService.findAll();
    };

    /**
     * find by id
     */
    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getOneById(@PathVariable Long restaurantId){
        Optional<Restaurant> restaurant = this.restaurantService.findOneById(restaurantId);
        if (restaurant.isPresent()){
            return ResponseEntity.ok(restaurant.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Restaurant not found"));
    };


    /**
     * Save restaurant
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Restaurant restaurant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.save(restaurant));
    }

    /**
     * Update by id
     */

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateOneById(@PathVariable Long restaurantId, @RequestBody @Valid Restaurant restaurant){
        Restaurant rest = restaurantService.updateOneById(restaurantId, restaurant);
        return ResponseEntity.status(HttpStatus.OK).body(rest);
    }

    /**
     * Disabled restaurant or enabled
     * @param restaurantId
     */
    @PutMapping("/{restaurantId}/disabled")
    public ResponseEntity<Restaurant> disableOneById(@PathVariable Long restaurantId){
        Restaurant restaurant = restaurantService.disableOneById(restaurantId);
        return null;
    }


}
