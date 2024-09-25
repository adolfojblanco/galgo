package galgo.com.backend.services.impl;

import galgo.com.backend.models.Role;
import galgo.com.backend.models.User;
import galgo.com.backend.repositories.RoleRepository;
import galgo.com.backend.repositories.UserRepository;
import galgo.com.backend.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username: %s not found", username));
        }
        User user = optionalUser.orElseThrow();

        /*
         * Convert roles for user
         */
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }

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
    public User save(User user, String role) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> optionalRole = roleRepository.findByName(role);
        if (optionalRole.isPresent()) {
            user.setRoles(roles);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Save a user with restaurant
     */
    @Override
    public User restaurantUser(User user, String role) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> optionalRole = roleRepository.findByName(role);
        if (optionalRole.isPresent()) {
            roles.add(optionalRole.get());
            user.setRoles(roles);
        }
        user.setToken(passwordEncoder.encode(new Date().toString()));
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
