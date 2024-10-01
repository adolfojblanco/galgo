package galgo.com.backend.controllers;


import galgo.com.backend.models.Address;
import galgo.com.backend.services.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private IAddressService addressService;


    /**
     * Create one address
     */
    @PostMapping
    public ResponseEntity<?> createOne(@RequestBody @Valid Address address){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.save(address));
    }

    /**
     * Find by id
     */
    @GetMapping("/addressId")
    public ResponseEntity<?> getOneById(@PathVariable Long addressId){
        Optional<Address> addressOptional = this.addressService.findOneById(addressId);
        if (addressOptional.isPresent()){
            return ResponseEntity.ok(addressOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Address not found"));
    }

    /**
     * Update by id
     */
    @PutMapping("/addressId")
    public ResponseEntity<Address> updateOneById(@PathVariable Long addressId, @RequestBody @Valid Address address){
        Address addressNew = addressService.save(address);
        return ResponseEntity.status(HttpStatus.OK).body(addressNew);
    }

    /**
     * Disabled address by id
     * @param addressId
     * @return null
     */
    @PutMapping("/addressId/disabled")
    public ResponseEntity<Address> disabledOneById(@PathVariable Long addressId){
        Address address = addressService.disableOneById(addressId);
        return null;
    }

    /**
     * Add address to restaurant
     * @param restaurantId
     * @param address
     * @return restaurant
     */
    @PostMapping("/{restaurantId}/add-address")
    public ResponseEntity<?> addRestaurantAddress(@PathVariable Long restaurantId, @RequestBody @Valid Address address){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.addRestaurantAddress(restaurantId, address));
    }

}
