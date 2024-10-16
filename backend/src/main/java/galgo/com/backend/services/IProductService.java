package galgo.com.backend.services;

import galgo.com.backend.dto.ProductDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IProductService {


    List<ProductDTO> findAll();

    Optional<ProductDTO> findById(Long id);

    ProductDTO save(String username, ProductDTO productDTO);

    Optional<ProductDTO> updateById(Long id);

    ProductDTO enabledProduct(Long id);

}
