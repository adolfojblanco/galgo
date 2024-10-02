package galgo.com.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @Column(unique=true)
    @NotBlank(message = "El nombre del restaurante no puede estar vacio")
    @Size(min = 3,max=30,message = "El nombre del restaurante debe tener al menos 3 caracteres y un maximo 30")
    private String restaurantName;

    @NotNull(message = "local phone field should not be null")
    private String localPhone;

    @NotNull(message = "mobileNumber field should not be null")
    @NotBlank
    private String mobilePhone;

    @ManyToOne(cascade = CascadeType.ALL)
    private RestaurantType restaurantType;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Category> categories;


}
