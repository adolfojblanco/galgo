package galgo.com.backend.mappers;

import galgo.com.backend.dto.ProductDTO;
import galgo.com.backend.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    List<ProductDTO> productsToProductsDTO(List<Product> products);

    ProductDTO productToproductDTO(Product product);

    Product productDTOtoProduct(ProductDTO productDTO);


}
