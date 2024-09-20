package galgo.com.backend.controllers;


import galgo.com.backend.models.RestaurantType;
import galgo.com.backend.services.IRestaurantTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants/types")
public class RestaurantTypeController {

    @Autowired
    private IRestaurantTypeService restaurantTypeService;


    @GetMapping
    public List<RestaurantType> getAll(){
        return this.restaurantTypeService.findAll();
    }
}
