package galgo.com.backend.services;

import galgo.com.backend.dto.RestaurantDTO;
import galgo.com.backend.models.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantService {

    public List<RestaurantDTO> findAll();

    public Optional<RestaurantDTO> findOneById(Long restaurantId);

    public Restaurant save(RestaurantDTO restaurant);

    public void deleteById(Long restaurantId);

    public Restaurant updateOneById(RestaurantDTO restaurantDTO);

    public Restaurant disableOneById(Long restaurantId);
}
