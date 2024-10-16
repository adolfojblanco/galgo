package galgo.com.backend.services.impl;

import galgo.com.backend.dto.ProductDTO;
import galgo.com.backend.services.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Override
    public List<ProductDTO> findAll() {

        return List.of();
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductDTO save(String username, ProductDTO productDTO) {
        return null;
    }

    @Override
    public Optional<ProductDTO> updateById(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductDTO enabledProduct(Long id) {
        return null;
    }
}
