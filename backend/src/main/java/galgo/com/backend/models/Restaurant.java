package galgo.com.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
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
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private Boolean enabled;

    @JsonIgnoreProperties({"handler", "hibernateLazyInitializer"}) // garbage fields
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    public Restaurant() {
        this.products = new ArrayList<>();
    }

}
