package galgo.com.backend.dto;

import galgo.com.backend.models.Address;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO implements Serializable {

    private Long restaurantId;

    private String restaurantName;

    private String localPhone;

    private String mobilePhone;

    private boolean enabled;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

}
