package galgo.com.backend.controllers;


import galgo.com.backend.models.Restaurant;
import galgo.com.backend.services.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
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
    public List<Restaurant> getAll(){
        return this.restaurantService.findAll();
    };

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getOneById(@PathVariable Long id){
        Optional<Restaurant> restaurant = this.restaurantService.findById(id);
        if (restaurant.isPresent()){
            return ResponseEntity.ok(restaurant.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Restaurant not found"));
    };

    public ResponseEntity<?> create(@RequestBody Restaurant restaurant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.save(restaurant));
    }


}
