package galgo.com.backend.repositories;

import galgo.com.backend.models.RestaurantType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantTypeRepository extends CrudRepository<RestaurantType, Long> {
    Optional<RestaurantType> findByName(String name);
}
