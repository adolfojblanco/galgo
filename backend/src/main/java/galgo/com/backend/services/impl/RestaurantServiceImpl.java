package galgo.com.backend.services.impl;

import galgo.com.backend.dto.RestaurantDTO;
import galgo.com.backend.models.Address;
import galgo.com.backend.models.Restaurant;
import galgo.com.backend.models.User;
import galgo.com.backend.repositories.RestaurantRepository;
import galgo.com.backend.repositories.RestaurantTypeRepository;
import galgo.com.backend.repositories.UserRepository;
import galgo.com.backend.services.IRestaurantService;
import galgo.com.backend.services.IUserService;
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

    @Autowired private UserRepository userRepository;
    @Autowired private RestaurantRepository restaurantRepository;
    @Autowired private RestaurantTypeRepository restaurantTypeRepository;
    @Autowired private SendEmail sendEmail;
    @Autowired
    private IUserService userService;



    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findOneById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    @Override
    public Restaurant save(RestaurantDTO restaurant) {

        User user = new User();
        user.setFirstName(restaurant.getFirstName());
        user.setLastName(restaurant.getLastName());
        user.setUsername(restaurant.getUsername());
        user.setEmail(restaurant.getEmail());
        user.setEnabled(true);
        User userDb = userRepository.save(user);


        Restaurant rest = new Restaurant();
        rest.setRestaurantName(restaurant.getRestaurantName());
        rest.setLocalPhone(restaurant.getLocalPhone());
        rest.setMobilePhone(restaurant.getMobilePhone());
        rest.setUser(userDb);
        rest.setEnabled(false);
        return restaurantRepository.save(rest);
    }

    @Override
    public void deleteById(Long restaurantId) {

    }

    @Override
    public Restaurant updateOneById(Long restaurantId, Restaurant restaurant) {
        Restaurant rest = restaurantRepository.findById(restaurantId).orElseThrow(null);
        rest.setRestaurantName(restaurant.getRestaurantName());
        rest.setLocalPhone(restaurant.getLocalPhone());
        rest.setMobilePhone(restaurant.getMobilePhone());
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
