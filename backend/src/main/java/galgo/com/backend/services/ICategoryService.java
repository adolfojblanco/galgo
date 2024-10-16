package galgo.com.backend.services;

import galgo.com.backend.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {


    List<CategoryDTO> findAll();

    List<CategoryDTO> findAllEnabled();

    Optional<CategoryDTO> findById(Long id);

    CategoryDTO save(CategoryDTO categoryDTO);

    CategoryDTO updateById(CategoryDTO categoryDTO);

    Optional<CategoryDTO> deleteById(Long id);

    CategoryDTO enabledCategory(Long id);

}
