package galgo.com.backend.mappers;

import galgo.com.backend.dto.RestaurantTypeDTO;
import galgo.com.backend.models.RestaurantType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantTypeMapper {

    RestaurantTypeMapper INSTANCE = Mappers.getMapper(RestaurantTypeMapper.class);


    List<RestaurantTypeDTO> restaurantTypeToRestaurantTypeDTOList(List<RestaurantType> restaurantTypes);

    RestaurantTypeDTO restaurantTypeToRestaurantTypeDTO(RestaurantType restaurantType);

    RestaurantType restaurantTypeDTOToRestaurantType(RestaurantTypeDTO restaurantTypeDTO);

}
