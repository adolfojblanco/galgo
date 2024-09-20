package galgo.com.backend.services;

import galgo.com.backend.models.RestaurantType;

import java.util.List;
import java.util.Optional;

public interface IRestaurantTypeService {

    List<RestaurantType> findAll();

    Optional<RestaurantType> findById(Long id);

    RestaurantType updateById(Long id, RestaurantType restaurantType);

    RestaurantType save(RestaurantType restaurantType);
}
