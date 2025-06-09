package az.sense.rasimkafesi.services.impls;

import az.sense.rasimkafesi.dtos.categoryDtos.CategoryCreateDto;
import az.sense.rasimkafesi.dtos.categoryDtos.CategoryDto;
import az.sense.rasimkafesi.dtos.categoryDtos.CategoryUpdateDto;
import az.sense.rasimkafesi.models.Category;
import az.sense.rasimkafesi.repositories.CategoryRepository;
import az.sense.rasimkafesi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addCategory(CategoryCreateDto categoryCreateDto) {
        Category findCategory = categoryRepository.findByName(categoryCreateDto.getName());
        if (findCategory != null) {
            return false;
        }
        Category category = new Category();
        category.setName(categoryCreateDto.getName());
        categoryRepository.save(category);
        return true;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public void updateCategory(CategoryUpdateDto categoryUpdateDto, Long id) {
      Category category = categoryRepository.findById(id).orElseThrow();
      category.setName(categoryUpdateDto.getName());
      categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
      List<Category> categories=categoryRepository.findAll();
      return categories.stream().map(x->modelMapper.map(x,CategoryDto.class)).toList();
    }

    @Override
    public CategoryUpdateDto findUpdateCategory(Long id) {
       Category category=categoryRepository.findById(id).orElseThrow();
       return  modelMapper.map(category,CategoryUpdateDto.class);
    }
}
