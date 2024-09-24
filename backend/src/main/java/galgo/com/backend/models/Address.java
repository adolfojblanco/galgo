package galgo.com.backend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    private String buildingNumber;

    private String floorNumber;

    private String doorNumber;

    private String street;

    private String area;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    private String latitude;

    private String longitude;

    private boolean active;
}
