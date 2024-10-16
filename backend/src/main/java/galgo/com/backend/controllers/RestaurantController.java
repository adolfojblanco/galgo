package galgo.com.backend.controllers;

import galgo.com.backend.dto.ApiResponse;
import galgo.com.backend.dto.RestaurantDTO;
import galgo.com.backend.dto.RestaurantUserSaveRequest;
import galgo.com.backend.models.Restaurant;
import galgo.com.backend.services.IRestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getAll(){
        return this.restaurantService.findAll();
    };

    /** find by id */
    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getOneById(@PathVariable Long restaurantId){
        Optional<RestaurantDTO> restaurant = this.restaurantService.findOneById(restaurantId);
        if (restaurant.isPresent()){
            return ResponseEntity.ok(restaurant.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Restaurant not found"));
    };

    /** Get Restaurant By logged user **/
    @GetMapping("/my-restaurant")
    public ResponseEntity<ApiResponse> restaurantByUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        RestaurantDTO restaurant = restaurantService.findByUsername(auth.getName()).orElseThrow();
        ApiResponse response = new ApiResponse();
        response.setData(restaurant);
        response.setCode(200L);
        return ResponseEntity.ok(response);
    }

    /** Save restaurant */
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody RestaurantUserSaveRequest restaurant, BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.save(restaurant));
    }

    /** Update by id */
    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateOneById(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant){
        Restaurant rest = restaurantService.updateOneById(restaurantId, restaurant);
        return ResponseEntity.status(HttpStatus.OK).body(rest);
    }

    /** Disabled restaurant or enabled, have an address  */
    @PutMapping("/{restaurantId}/activate")
    public ResponseEntity<?> disableOneById(@PathVariable Long restaurantId) {
        Optional<RestaurantDTO> restaurant = restaurantService.findOneById(restaurantId);
        if (restaurant.isPresent()) {
            if (restaurant.get().getAddress() != null) {
                ApiResponse response = new ApiResponse();
                response.setData(restaurant);
                response.setCode(200L);
                restaurantService.disableOneById(restaurantId);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("error", "No se puede activar, debes añadir una dirección"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Restaurant not found"));
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse> activeRestaurants() {
        List<RestaurantDTO> restaurants = restaurantService.activeRestaurants();
        ApiResponse response = new ApiResponse();
        response.setData(restaurants);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
