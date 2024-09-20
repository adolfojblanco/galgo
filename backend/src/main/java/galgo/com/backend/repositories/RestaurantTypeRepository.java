package galgo.com.backend.repositories;

import galgo.com.backend.models.RestaurantType;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantTypeRepository extends CrudRepository<RestaurantType, Long> {
}
