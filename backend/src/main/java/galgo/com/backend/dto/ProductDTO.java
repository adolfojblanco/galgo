package galgo.com.backend.dto;

import galgo.com.backend.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long productId;

    private String productName;

    private String description;

    private BigDecimal price;

    private boolean enabled;

    private Category category;

}
