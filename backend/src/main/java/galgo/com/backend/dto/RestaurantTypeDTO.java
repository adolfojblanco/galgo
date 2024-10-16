package galgo.com.backend.dto;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RestaurantTypeDTO implements Serializable {
    private Long id;
    private String name;
    private Boolean enabled;
}
