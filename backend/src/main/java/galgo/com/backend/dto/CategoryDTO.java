package galgo.com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long categoryId;

    private String categoryName;

    private Boolean enabled;

}
