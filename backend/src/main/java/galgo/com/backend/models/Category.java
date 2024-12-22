package galgo.com.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "categories", uniqueConstraints = {
        @UniqueConstraint(name = "category_name", columnNames = {"category_name"}),
        @UniqueConstraint(name = "restaurant_id", columnNames = {"restaurant_id"}),
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(unique = true, name = "category_name", nullable = false)
    private String categoryName;

    @NotBlank
    private Boolean enabled;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

}
