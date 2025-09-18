package libraryManagement.test.service;

import libraryManagement.test.dto.CategoryDto;
import libraryManagement.test.model.Category;
import libraryManagement.test.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return convertToDto(category);
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = convertToEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return convertToDto(savedCategory);
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDto.getName());
        Category updatedCategory = categoryRepository.save(category);
        return convertToDto(updatedCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDto convertToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(category.getCategoryId());
        dto.setName(category.getName());
        return dto;
    }

    private Category convertToEntity(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}