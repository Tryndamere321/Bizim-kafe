package az.sense.rasimkafesi.services;

import az.sense.rasimkafesi.dtos.categoryDtos.CategoryCreateDto;
import az.sense.rasimkafesi.dtos.categoryDtos.CategoryDto;
import az.sense.rasimkafesi.dtos.categoryDtos.CategoryUpdateDto;
import az.sense.rasimkafesi.models.Category;

import java.util.List;

public interface CategoryService {
    boolean addCategory(CategoryCreateDto categoryCreateDto);
    void deleteCategory(Long id);
    CategoryDto getCategory(Long id);
    void updateCategory(CategoryUpdateDto categoryUpdateDto,Long id);
    List<CategoryDto> getAllCategories();
    CategoryUpdateDto findUpdateCategory(Long id);
}
