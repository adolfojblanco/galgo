package galgo.com.backend.dto;

import galgo.com.backend.models.Address;
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

    private String manager;

    private String localPhone;

    private String mobilePhone;

    private Address address;

}
