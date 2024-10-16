package galgo.com.backend.services.impl;

import galgo.com.backend.dto.CategoryDTO;

import galgo.com.backend.mappers.CategoryMapper;
import galgo.com.backend.models.Category;
import galgo.com.backend.repositories.CategoryRepository;
import galgo.com.backend.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return CategoryMapper.INSTANCE.categoriesToCategoriesDTO(categories);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> findAllEnabled() {
        List<Category> categories = this.categoryRepository.findByEnabledTrue();
        return CategoryMapper.INSTANCE.categoriesToCategoriesDTO(categories);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<CategoryDTO> findById(Long id) {
        Optional <Category> category = Optional.of(categoryRepository.findById(id).orElseThrow( () -> new RuntimeException("Exception")));
        return Optional.ofNullable(CategoryMapper.INSTANCE.categoryToCategoryDTO(category.get()));
    }

    @Transactional
    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.INSTANCE.categoryDtoToCategory(categoryDTO);
        Category newCategory = categoryRepository.save(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(newCategory);
    }

    @Transactional
    @Override
    public CategoryDTO updateById(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.INSTANCE.categoryDtoToCategory(categoryDTO);
        Category catUpdate = categoryRepository.save(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(catUpdate);
    }

    @Transactional
    @Override
    public Optional<CategoryDTO> deleteById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        category.ifPresent(cat -> {
            categoryRepository.deleteById(cat.getCategoryId());
        });
        return Optional.ofNullable(CategoryMapper.INSTANCE.categoryToCategoryDTO(category.orElseThrow(null)));
    }

    @Transactional
    @Override
    public CategoryDTO enabledCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            category.get().setEnabled(!category.get().getEnabled());
            return CategoryMapper.INSTANCE.categoryToCategoryDTO(categoryRepository.save(category.get()));
        }
        return null;
    }
}
