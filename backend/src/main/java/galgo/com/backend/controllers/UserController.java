package galgo.com.backend.controllers;

import galgo.com.backend.models.User;
import galgo.com.backend.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = { "http://localhost:4200" })
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * Update user
     * @return userOptional
     */

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {

        Optional<User> userOptional = userService.update(user, id);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional);
        }
        return ResponseEntity.notFound().build();
    }


}
