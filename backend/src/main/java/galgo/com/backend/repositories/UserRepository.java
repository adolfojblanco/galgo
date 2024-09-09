package galgo.com.backend.repositories;

import galgo.com.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Search user for username, for login
    Optional<User> findByUsername(String name);

}
