package galgo.com.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @Column(unique=true)
    @NotBlank(message = "El nombre del restaurant no puede estar vacio")
    @Size(min = 3,max=30,message = "El nombre del restaurante debe tener al menos 3 caracteres y un maximo 30")
    private String restaurantName;

    @NotNull(message = "manager Name should not be null")
    @Size(min = 3,max=30,message = "name of min length should be 3 and max be 30")
    private String manager;

    @NotNull(message = "local phone field should not be null")
    private String localPhone;

    @NotNull(message = "mobileNumber field should not be null")
    @NotBlank
    private String mobilePhone;

    @NotNull(message = "email field should not be null")
    @Column(unique = true)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    private RestaurantType restaurantType;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @NotNull
    private boolean enabled;


}
