package galgo.com.backend.repositories;

import galgo.com.backend.models.Category;
import galgo.com.backend.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByEnabledTrue();
}
