package galgo.com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private boolean enabled;
}