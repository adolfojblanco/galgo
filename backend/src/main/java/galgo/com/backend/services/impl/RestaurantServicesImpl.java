package galgo.com.backend.services.impl;

import galgo.com.backend.models.Restaurant;
import galgo.com.backend.repositories.RestaurantRepository;
import galgo.com.backend.services.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServicesImpl implements IRestaurantService {

    @Autowired private RestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteById(Long id) {
        restaurantRepository.deleteById(id);
    }
}
