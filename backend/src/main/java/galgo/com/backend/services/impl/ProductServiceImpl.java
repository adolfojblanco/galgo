package galgo.com.backend.services.impl;

import galgo.com.backend.dto.ProductDTO;
import galgo.com.backend.dto.RestaurantDTO;
import galgo.com.backend.mappers.ProductMapper;
import galgo.com.backend.mappers.RestaurantMapper;
import galgo.com.backend.models.Product;
import galgo.com.backend.models.Restaurant;
import galgo.com.backend.repositories.ProductRepository;
import galgo.com.backend.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.INSTANCE.productsToProductsDTO(products);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ProductDTO> findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Exception"));
        return Optional.ofNullable(ProductMapper.INSTANCE.productToproductDTO(product));
    }

    @Override
    public ProductDTO save(RestaurantDTO restaurantDTO, ProductDTO productDTO) {
        Restaurant restaurant = RestaurantMapper.INSTANCE.restaurantDtoToRestaurant(restaurantDTO);
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
        product.setEnabled(productDTO.isEnabled());
        product.setRestaurant(restaurant);
        Product newProduct = this.productRepository.save(product);
        return ProductMapper.INSTANCE.productToproductDTO(newProduct);
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
