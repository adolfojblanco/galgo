package galgo.com.backend.controllers;

import galgo.com.backend.dto.RestaurantDTO;
import galgo.com.backend.models.Address;
import galgo.com.backend.models.Restaurant;
import galgo.com.backend.services.IAddressService;
import galgo.com.backend.services.IRestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @Autowired
    private IAddressService addressService;

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
    public ResponseEntity<?> create(@Valid @RequestBody RestaurantDTO restaurant, BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.save(restaurant));
    }

    /**
     * Update by id
     */

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateOneById(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant){
        Restaurant rest = restaurantService.updateOneById(restaurantId, restaurant);
        return ResponseEntity.status(HttpStatus.OK).body(rest);
    }

    /**
     * Disabled restaurant or enabled, have an address
     *
     * @param restaurantId
     */
    @PutMapping("/{restaurantId}/activate")
    public ResponseEntity<?> disableOneById(@PathVariable Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantService.findOneById(restaurantId);
        if (restaurant.isPresent()) {
            if (restaurant.get().getAddress() != null) {
                restaurantService.disableOneById(restaurantId);
                return ResponseEntity.status(HttpStatus.OK).body(restaurant);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("error", "No se puede activar, debes añadir una dirección"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Restaurant not found"));
    }

    /**
     * Add address to restaurant
     * @param restaurantId
     * @param address
     * @return restaurant
     */
    @PutMapping("/{restaurantId}/add-address")
    public ResponseEntity<?> addAddress(@PathVariable Long restaurantId, @RequestBody @Valid Address address){
        Address newAddress =  addressService.save(address);
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.addAddress(restaurantId, newAddress));
    }


}
