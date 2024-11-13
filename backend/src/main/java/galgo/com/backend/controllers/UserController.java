package galgo.com.backend.controllers;

import galgo.com.backend.dto.ApiResponse;
import galgo.com.backend.dto.UserDTO;
import galgo.com.backend.models.User;
import galgo.com.backend.request.ConfirmAccountRequest;
import galgo.com.backend.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = { "http://localhost:4200" })
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * Update user
     * @return userOptional
     */

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {

        Optional<UserDTO> userOptional = userService.update(user, id);
        if (userOptional.isPresent()) {
            ApiResponse response = new ApiResponse();
            response.setCode(200);
            response.setData(userOptional);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/confirm-account/{token}")
    public ResponseEntity<?> confirmAccount(@Valid @RequestBody ConfirmAccountRequest request, @PathVariable String token) {
        Optional<UserDTO> user = userService.confirmAccount(token, request);
        return ResponseEntity.ok(user);
    }

}
