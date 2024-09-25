package galgo.com.backend.services;

import galgo.com.backend.dto.RestaurantDTO;
import galgo.com.backend.models.Address;
import galgo.com.backend.models.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantService {

    List<Restaurant> findAll();

    Optional<Restaurant> findOneById(Long restaurantId);

    Restaurant save(RestaurantDTO restaurant);

    void deleteById(Long restaurantId);

    Restaurant updateOneById(Long restaurantId, Restaurant restaurantDTO);

    Restaurant disableOneById(Long restaurantId);

    Restaurant addAddress(Long restaurantId, Address address);
}
