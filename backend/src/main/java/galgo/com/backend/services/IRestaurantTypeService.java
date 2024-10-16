package galgo.com.backend.services;

import galgo.com.backend.dto.RestaurantTypeDTO;

import java.util.List;
import java.util.Optional;

public interface IRestaurantTypeService {

    List<RestaurantTypeDTO> findAll();

    Optional<RestaurantTypeDTO> findById(Long id);

    RestaurantTypeDTO updateById(Long restTypeId, RestaurantTypeDTO restaurantTypeDTO);

    RestaurantTypeDTO save(RestaurantTypeDTO restaurantTypeDTO);

    boolean deleteById(Long id);

}
