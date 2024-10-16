package galgo.com.backend.mappers;

import galgo.com.backend.dto.RestaurantDTO;
import galgo.com.backend.models.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    List<RestaurantDTO> restaurantsToRestaurantsDTO(List<Restaurant> restaurants);

    RestaurantDTO restaurantToRestaurantDTO(Restaurant restaurant);

    Restaurant restaurantDtoToRestaurant(RestaurantDTO restaurantDTO);

}
