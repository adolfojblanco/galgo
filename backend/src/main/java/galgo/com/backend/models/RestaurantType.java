package galgo.com.backend.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "restaurant_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RestaurantType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 3,max=30,message = "El nombre debe tener al menos 3 caracteres y un maximo 30")
    private String name;

    private Boolean enabled;


}
