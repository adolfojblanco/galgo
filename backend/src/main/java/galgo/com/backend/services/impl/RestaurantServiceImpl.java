package galgo.com.backend.services.impl;

import galgo.com.backend.dto.RestaurantUserSaveRequest;
import galgo.com.backend.models.*;
import galgo.com.backend.repositories.RestaurantRepository;
import galgo.com.backend.repositories.RestaurantTypeRepository;
import galgo.com.backend.repositories.RoleRepository;
import galgo.com.backend.repositories.UserRepository;
import galgo.com.backend.services.IRestaurantService;
import galgo.com.backend.services.IUserService;

import galgo.com.backend.utilities.SendEmail;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class RestaurantServiceImpl implements IRestaurantService {
    private final Logger log = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    @Autowired private RoleRepository roleRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private RestaurantRepository restaurantRepository;
    @Autowired private RestaurantTypeRepository restaurantTypeRepository;
    @Autowired private PasswordEncoder encoder;
    @Autowired private SendEmail emailingService;

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
    public Restaurant save(RestaurantUserSaveRequest request) {
        String token = encoder.encode(new Date(System.currentTimeMillis()).toString());

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setEnabled(true);
        user.setToken(token.substring(10, token.length()-1));

        List<Role> roles = new ArrayList<>();
        Optional<Role> optionalRole = roleRepository.findByName("ROLE_BUSINESS");
        if (optionalRole.isPresent()) {
            roles.add(optionalRole.get());
            user.setRoles(roles);
        }
        User userDb = userRepository.save(user);

        RestaurantType restaurantType = restaurantTypeRepository.findById(request.getRestaurantType().getId()).orElseThrow();

        Restaurant rest = new Restaurant();
        rest.setRestaurantName(request.getRestaurantName());
        rest.setLocalPhone(request.getLocalPhone());
        rest.setMobilePhone(request.getMobilePhone());
        rest.setRestaurantType(restaurantType);
        rest.setUser(userDb);
        rest.setEnabled(false);
        return restaurantRepository.save(rest);

    }

    @Override
    public void deleteById(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
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

}
