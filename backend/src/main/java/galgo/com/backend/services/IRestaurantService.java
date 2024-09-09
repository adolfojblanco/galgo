package galgo.com.backend.services;

import galgo.com.backend.models.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantService {

    public List<Restaurant> findAll();

    public Optional<Restaurant> findById(Long id);

    public Restaurant save(Restaurant restaurant);

    public void deleteById(Long id);
}
