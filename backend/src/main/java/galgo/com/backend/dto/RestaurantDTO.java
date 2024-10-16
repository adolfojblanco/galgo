package galgo.com.backend.dto;

import galgo.com.backend.models.Address;
import galgo.com.backend.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RestaurantDTO {
    private Long restaurantId;

    private String restaurantName;

    private String localPhone;

    private String mobilePhone;

    private RestaurantTypeDTO restaurantType;

    private UserDTO user;

    private Address address;

    private Boolean enabled;

    private List<Product> products;
}
