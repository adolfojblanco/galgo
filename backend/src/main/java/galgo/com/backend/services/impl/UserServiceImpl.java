package galgo.com.backend.services.impl;

import galgo.com.backend.dto.UserDTO;
import galgo.com.backend.mappers.UserMapper;
import galgo.com.backend.models.Role;
import galgo.com.backend.models.User;
import galgo.com.backend.repositories.RoleRepository;
import galgo.com.backend.repositories.UserRepository;
import galgo.com.backend.request.ConfirmAccountRequest;
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
import java.util.List;
import java.util.Objects;
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
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.usersToUsersDTO(users);
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserDTO> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return Optional.ofNullable(UserMapper.INSTANCE.userToUserDTO(user.get()));
    }


    @Transactional
    @Override
    public UserDTO save(User user, String role) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> optionalRole = roleRepository.findByName(role);
        if (optionalRole.isPresent()) {
            user.setRoles(roles);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(user);
        return UserMapper.INSTANCE.userToUserDTO(newUser);
    }

    @Transactional
    @Override
    public Optional<UserDTO> update(User user, Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User userDb = userOptional.get();
            userDb.setFirstName(user.getFirstName());
            userDb.setLastName(user.getLastName());
            userDb.setEmail(user.getEmail());
            userDb.setEnabled(user.getEnabled());
            userDb.setUsername(user.getUsername());
            return Optional.ofNullable(UserMapper.INSTANCE.userToUserDTO(userDb));
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> confirmAccount(String token, ConfirmAccountRequest request) {
        Optional<User> userToken = userRepository.findByToken(token);
        if (userToken.isPresent()){
            Optional<User> userOptional = userRepository.findByUsername(request.getUsername());

            if (userOptional.isPresent()) {
                if (Objects.equals(userToken.get().getToken(), userOptional.get().getToken())) {
                    User userDb = userOptional.get();
                    userDb.setToken("");
                    userDb.setEnabled(true);
                    userDb.setPassword(passwordEncoder.encode(request.getPassword()));
                    return Optional.of(UserMapper.INSTANCE.userToUserDTO(userRepository.save(userDb)));
                }
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

}
