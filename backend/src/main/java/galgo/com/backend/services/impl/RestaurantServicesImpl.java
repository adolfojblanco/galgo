package galgo.com.backend.services.impl;

import galgo.com.backend.models.Restaurant;
import galgo.com.backend.repositories.RestaurantRepository;
import galgo.com.backend.services.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServicesImpl implements IRestaurantService {

    @Autowired private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findOneById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        Restaurant rest = new Restaurant();
        rest.setRestaurantName(restaurant.getRestaurantName());
        rest.setManager(restaurant.getManager());
        rest.setLocalPhone(restaurant.getLocalPhone());
        rest.setMobilePhone(restaurant.getMobilePhone());
        rest.setEnabled(true);
        return restaurantRepository.save(rest);
    }

    @Override
    public void deleteById(Long restaurantId) {

    }

    @Override
    public Restaurant updateOneById(Long restaurantId, Restaurant restaurant) {
        Restaurant rest = restaurantRepository.findById(restaurantId).orElseThrow(null);
        rest.setRestaurantName(restaurant.getRestaurantName());
        rest.setManager(restaurant.getManager());
        rest.setLocalPhone(restaurant.getLocalPhone());
        rest.setMobilePhone(restaurant.getMobilePhone());
        return restaurantRepository.save(rest);
    }

    @Override
    public Restaurant disableOneById(Long restaurantId) {
        return null;
    }
}
