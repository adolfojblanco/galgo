package galgo.com.backend.mappers;

import galgo.com.backend.dto.RestaurantDTO;
import galgo.com.backend.models.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    RestaurantDTO restaurantToRestaurantDTO(Restaurant restaurant);

    Restaurant restaurantDTOtoRestaurant(RestaurantDTO restaurantDTO);

}
