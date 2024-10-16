package galgo.com.backend.repositories;

import galgo.com.backend.models.Restaurant;
import galgo.com.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findOneByUser(User user);

    List<Restaurant> findByEnabledTrue();

}
