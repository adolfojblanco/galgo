package galgo.com.backend.services.impl;

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
    public List<RestaurantType> findAll() {
        return (List<RestaurantType>) this.repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<RestaurantType> findById(Long id) {
        return this.repository.findById(id);
    }

    @Transactional
    @Override
    public RestaurantType updateById(Long id, RestaurantType restaurantType) {
        return null;
    }

    @Transactional
    @Override
    public RestaurantType save(RestaurantType restaurantType) {
        RestaurantType type = new RestaurantType();
        type.setName(restaurantType.getName());
        type.setEnabled(true);
        return repository.save(type);
    }
}
