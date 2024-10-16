package galgo.com.backend.mappers;

import galgo.com.backend.dto.CategoryDTO;
import galgo.com.backend.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    List<CategoryDTO> categoriesToCategoriesDTO(List<Category> categories);

    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryDtoToCategory(CategoryDTO categoryDTO);


}
