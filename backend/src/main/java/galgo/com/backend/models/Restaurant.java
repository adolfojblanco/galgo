package galgo.com.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @NotNull(message = "Restaurant Name should not be null")
    @Size(min = 3,max=30,message = "Restaurant name of min length should be 3 and max be 30")
    private String restaurantName;

    @NotNull(message = "manager Name should not be null")
    @Size(min = 3,max=30,message = "name of min length should be 3 and max be 30")
    private String manager;

    @NotNull(message = "mobileNumber field should not be null")
    private String localPhone;

    @NotNull(message = "mobileNumber field should not be null")
    private String mobilePhone;

    @NotNull(message = "Address should not be null")
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @NotNull
    private boolean active;

}
