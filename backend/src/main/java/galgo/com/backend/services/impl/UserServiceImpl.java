package galgo.com.backend.services.impl;

import galgo.com.backend.models.Restaurant;
import galgo.com.backend.models.User;
import galgo.com.backend.repositories.UserRepository;
import galgo.com.backend.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }


    @Transactional
    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public Optional<User> update(User user, Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User userDb = userOptional.get();
            userDb.setFirstName(user.getFirstName());
            userDb.setLastName(user.getLastName());
            userDb.setEmail(user.getEmail());
            userDb.setEnabled(user.isEnabled());
            userDb.setUsername(user.getUsername());
            return Optional.of(userRepository.save(userDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
    }
}
