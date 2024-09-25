package galgo.com.backend.services;

import galgo.com.backend.models.Restaurant;
import galgo.com.backend.models.User;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<User> findAll();

    public Optional<User> findById(Long id);

    public User save(User user, String role);

    public User restaurantUser(User user, String role);

    public Optional<User> update(User user, Long id);

    public void deleteById(Long id);
}
