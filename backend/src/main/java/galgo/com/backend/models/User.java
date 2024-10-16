package galgo.com.backend.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Size(min = 4, max = 12)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Column(unique = true)
    private String email;

    private String password;

    private Boolean enabled;

    private String token;

    @JsonIgnoreProperties({"handler", "hibernateLazyInitializer"}) // garbage fields
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles", // name of table
            joinColumns = {@JoinColumn(name = "user_id")}, // main relationship
            inverseJoinColumns = @JoinColumn(name = "role_id"), //inverse relationship
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})}
    )
    private List<Role> roles;


    public User() {
        this.roles = new ArrayList<>();
    }
}
