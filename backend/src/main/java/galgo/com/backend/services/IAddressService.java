package galgo.com.backend.services;

import galgo.com.backend.models.Address;

import java.util.Optional;

public interface IAddressService {

    Optional<Address> findOneById(Long addressId);

    Address save(Address address);

    void deleteById(Long addressId);

    Address updateOneById(Long addressId, Address address);

    Address disableOneById(Long addressId);
}
