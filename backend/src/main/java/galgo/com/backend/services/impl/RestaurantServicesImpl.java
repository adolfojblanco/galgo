package galgo.com.backend.services.impl;

import galgo.com.backend.dto.RestaurantDTO;
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
    public List<RestaurantDTO> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<RestaurantDTO> findOneById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    @Override
    public Restaurant save(RestaurantDTO restaurant) {
        return null;
    }

    @Override
    public Restaurant save(RestaurantDTO restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteById(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    @Override
    public Restaurant updateOneById(RestaurantDTO restaurantDTO) {
        return null;
    }

    @Override
    public Restaurant disableOneById(Long restaurantId) {
        return null;
    }
}
