package galgo.com.backend.services;

import galgo.com.backend.dto.RestaurantDTO;
import galgo.com.backend.dto.RestaurantUserSaveRequest;
import galgo.com.backend.models.Address;
import galgo.com.backend.models.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantService {

    List<Restaurant> findAll();

    Optional<RestaurantDTO> findOneById(Long restaurantId);

    Restaurant save(RestaurantUserSaveRequest restaurant);

    Restaurant updateOneById(Long restaurantId, Restaurant restaurantDTO);

    Restaurant disableOneById(Long restaurantId);

    Optional<RestaurantDTO> findByUsername(String username);

    List<RestaurantDTO> activeRestaurants();




}
