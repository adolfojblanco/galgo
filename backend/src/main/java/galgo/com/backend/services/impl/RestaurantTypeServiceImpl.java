package galgo.com.backend.services.impl;

import galgo.com.backend.dto.RestaurantTypeDTO;
import galgo.com.backend.mappers.RestaurantTypeMapper;
import galgo.com.backend.models.RestaurantType;
import galgo.com.backend.repositories.RestaurantTypeRepository;
import galgo.com.backend.services.IRestaurantTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantTypeServiceImpl implements IRestaurantTypeService {

    private final Logger log = LoggerFactory.getLogger(RestaurantTypeServiceImpl.class);

    @Autowired
    private RestaurantTypeRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<RestaurantTypeDTO> findAll() {
        List<RestaurantType> restaurantTypes = (List<RestaurantType>) repository.findAll();
        return RestaurantTypeMapper.INSTANCE.restaurantTypeToRestaurantTypeDTOList(restaurantTypes);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<RestaurantTypeDTO> findById(Long id) {
        RestaurantType restaurantType = repository.findById(id).orElse(null);
        return Optional.ofNullable(RestaurantTypeMapper.INSTANCE.restaurantTypeToRestaurantTypeDTO(restaurantType));
    }

    @Transactional
    @Override
    public RestaurantTypeDTO updateById(Long restTypeId, RestaurantTypeDTO restaurantTypeDTO) {
        RestaurantType restaurantType = RestaurantTypeMapper.INSTANCE.restaurantTypeDTOToRestaurantType(restaurantTypeDTO);
        restaurantType.setName(restaurantTypeDTO.getName());
        restaurantType.setEnabled(restaurantTypeDTO.getEnabled());
        RestaurantType updateRestType = repository.save(restaurantType);
        return RestaurantTypeMapper.INSTANCE.restaurantTypeToRestaurantTypeDTO(updateRestType);
    }


    @Transactional
    @Override
    public RestaurantTypeDTO save(RestaurantTypeDTO restaurantTypeDTO) {
        RestaurantType type = new RestaurantType();
        type.setName(restaurantTypeDTO.getName());
        type.setEnabled(true);
        return RestaurantTypeMapper.INSTANCE.restaurantTypeToRestaurantTypeDTO(repository.save(type));
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        Optional<RestaurantType> restaurantType = this.repository.findById(id);
        if (restaurantType.isPresent()){
            repository.deleteById(restaurantType.get().getId());
            return true;
        }
        return false;
    }
}
