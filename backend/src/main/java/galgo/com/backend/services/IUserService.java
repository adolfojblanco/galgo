package galgo.com.backend.services;

import galgo.com.backend.dto.UserDTO;
import galgo.com.backend.models.Restaurant;
import galgo.com.backend.models.User;
import galgo.com.backend.request.ConfirmAccountRequest;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserDTO> findAll();

    Optional<UserDTO> findByUsername(String username);

    Optional<UserDTO> findById(Long id);

    UserDTO save(User user, String role);

    Optional<UserDTO> update(User user, Long id);

    Optional<UserDTO> confirmAccount(String token, ConfirmAccountRequest request);

    void deleteById(Long id);
}
