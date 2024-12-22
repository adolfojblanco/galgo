package galgo.com.backend.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "restaurant_types", uniqueConstraints = {@UniqueConstraint(name = "name", columnNames = {"name"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RestaurantType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, name = "name")
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 3,max=30,message = "El nombre debe tener al menos 3 caracteres y un maximo 30")
    private String name;

    private Boolean enabled;

}
