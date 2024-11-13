package galgo.com.backend.services.impl;


import galgo.com.backend.models.Address;
import galgo.com.backend.models.Restaurant;
import galgo.com.backend.repositories.AddressRepository;
import galgo.com.backend.repositories.RestaurantRepository;
import galgo.com.backend.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Address> findOneById(Long addressId) {
        return addressRepository.findById(addressId);
    }

    @Transactional
    @Override
    public Address save(Address address) {
        Address addressNew = new Address();
        addressNew.setAddressName(address.getAddressName());
        addressNew.setStreet(address.getStreet());
        addressNew.setBuildingNumber(address.getBuildingNumber());
        addressNew.setPostalCode(address.getPostalCode());
        addressNew.setFloorNumber(address.getFloorNumber());
        addressNew.setDoorNumber(address.getDoorNumber());
        addressNew.setArea(address.getArea());
        addressNew.setCity(address.getCity());
        addressNew.setCountry(address.getCountry());
        addressNew.setLatitude(address.getLatitude());
        addressNew.setLongitude(address.getLongitude());
        addressNew.setEnabled(true);
        return addressRepository.save(addressNew);
    }

    @Transactional
    @Override
    public void deleteById(Long addressId) {
        addressRepository.deleteById(addressId);
    }

    @Transactional
    @Override
    public Address updateOneById(Long addressId, Address address) {
        Address addressBd = addressRepository.findById(addressId).orElseThrow();
        addressBd.setStreet(address.getStreet());
        addressBd.setBuildingNumber(address.getBuildingNumber());
        addressBd.setPostalCode(address.getPostalCode());
        addressBd.setFloorNumber(address.getFloorNumber());
        addressBd.setDoorNumber(address.getDoorNumber());
        addressBd.setArea(address.getArea());
        addressBd.setCity(address.getCity());
        addressBd.setCity(address.getCity());
        addressBd.setCountry(address.getCountry());
        addressBd.setLatitude(address.getLatitude());
        addressBd.setLongitude(address.getLongitude());
        return addressRepository.save(addressBd);
    }

    @Override
    public Address addRestaurantAddress(Long restaurantId, Address address) {
        Restaurant rest = this.restaurantRepository.findById(restaurantId).orElseThrow(null);
        if (rest != null) {
            Address addressNew = new Address();
            addressNew.setAddressName(address.getAddressName());
            addressNew.setStreet(address.getStreet());
            addressNew.setBuildingNumber(address.getBuildingNumber());
            addressNew.setPostalCode(address.getPostalCode());
            addressNew.setFloorNumber(address.getFloorNumber());
            addressNew.setDoorNumber(address.getDoorNumber());
            addressNew.setArea(address.getArea());
            addressNew.setCity(address.getCity());
            addressNew.setCountry(address.getCountry());
            addressNew.setLatitude(address.getLatitude());
            addressNew.setLongitude(address.getLongitude());
            addressNew.setEnabled(true);

            addressNew = this.addressRepository.save(addressNew);
            rest.setAddress(addressNew);
            this.restaurantRepository.save(rest);
            return addressNew;
        }
        return null;
    }

    @Transactional
    @Override
    public Address disableOneById(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow();
        address.setEnabled(!address.getEnabled());
        return address;
    }
}
