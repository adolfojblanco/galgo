package galgo.com.backend.services.impl;

import galgo.com.backend.dto.RestaurantDTO;
import galgo.com.backend.dto.RestaurantUserSaveRequest;
import galgo.com.backend.mappers.RestaurantMapper;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


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

    @Transactional(readOnly = true)
    @Override
    public Optional<RestaurantDTO> findOneById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
        return Optional.ofNullable(RestaurantMapper.INSTANCE.restaurantToRestaurantDTO(restaurant));
    }

    @Transactional
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

    @Transactional
    @Override
    public Restaurant updateOneById(Long restaurantId, Restaurant restaurant) {
        Restaurant rest = restaurantRepository.findById(restaurantId).orElseThrow(null);
        rest.setRestaurantName(restaurant.getRestaurantName());
        rest.setLocalPhone(restaurant.getLocalPhone());
        rest.setMobilePhone(restaurant.getMobilePhone());
        rest.setRestaurantType(restaurant.getRestaurantType());
        return restaurantRepository.save(rest);
    }

    @Transactional
    @Override
    public Restaurant disableOneById(Long restaurantId) {
        Restaurant rest = restaurantRepository.findById(restaurantId).orElseThrow(null);
        rest.setEnabled(!rest.getEnabled());
        return restaurantRepository.save(rest);

    }

    @Transactional(readOnly = true)
    @Override
    public Optional<RestaurantDTO> findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Restaurant restaurant = restaurantRepository.findOneByUser(user).orElseThrow();
        return Optional.of(RestaurantMapper.INSTANCE.restaurantToRestaurantDTO(restaurant));
    }

    @Transactional(readOnly = true)
    @Override
    public List<RestaurantDTO> activeRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findByEnabledTrue();
        return RestaurantMapper.INSTANCE.restaurantsToRestaurantsDTO(restaurants);
    }

}
