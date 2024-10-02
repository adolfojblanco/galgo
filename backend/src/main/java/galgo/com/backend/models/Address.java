package galgo.com.backend.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String addressName;

    private String buildingNumber;

    private String floorNumber;

    private String doorNumber;

    private String street;

    private String area;

    private String city;

    private String postalCode;

    private String country;

    private String latitude;

    private String longitude;

    private Boolean active;
}
