package galgo.com.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTypeDTO implements Serializable {
    private Long id;
    private String name;
    private Boolean enabled;
}
