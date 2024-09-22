package galgo.com.backend.services.impl;

import galgo.com.backend.models.Address;
import galgo.com.backend.models.Restaurant;
import galgo.com.backend.models.RestaurantType;
import galgo.com.backend.repositories.RestaurantRepository;
import galgo.com.backend.repositories.RestaurantTypeRepository;
import galgo.com.backend.services.IRestaurantService;
import galgo.com.backend.utilities.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements IRestaurantService {
    private final Logger log = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    @Autowired private RestaurantRepository restaurantRepository;
    @Autowired private RestaurantTypeRepository restaurantTypeRepository;
    @Autowired private SendEmail sendEmail;

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
        rest.setEmail(restaurant.getEmail());
        rest.setEnabled(false);

        RestaurantType type = restaurantTypeRepository.findById(restaurant.getRestaurantType().getId()).orElseThrow();
        rest.setRestaurantType(type);
        Restaurant newRest = restaurantRepository.save(rest);
        this.sendEmail.newAccount(restaurant.getEmail());
        return newRest;
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
        rest.setEmail(restaurant.getEmail());
        rest.setRestaurantType(restaurant.getRestaurantType());
        return restaurantRepository.save(rest);
    }

    @Override
    public Restaurant disableOneById(Long restaurantId) {
        Restaurant rest = restaurantRepository.findById(restaurantId).orElseThrow(null);
        rest.setEnabled(!rest.isEnabled());
        return restaurantRepository.save(rest);

    }

    @Override
    public Restaurant addAddress(Long restaurantId, Address address) {
        Restaurant rest = restaurantRepository.findById(restaurantId).orElseThrow(null);
        rest.setAddress(address);
        return restaurantRepository.save(rest);
    }
}
