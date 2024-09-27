package galgo.com.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import galgo.com.backend.models.Address;
import galgo.com.backend.models.RestaurantType;
import galgo.com.backend.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantUserSaveRequest {

    private Long restaurantId;

    private String restaurantName;

    private String localPhone;

    private String mobilePhone;

    private RestaurantType restaurantType;

    private Address address;

    private boolean enabled;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    private String token;

    private List<Role> roles;
}
