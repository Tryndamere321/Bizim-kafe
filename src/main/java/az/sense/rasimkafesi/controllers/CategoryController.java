package az.sense.rasimkafesi.controllers;

import az.sense.rasimkafesi.dtos.categoryDtos.CategoryCreateDto;
import az.sense.rasimkafesi.models.Category;
import az.sense.rasimkafesi.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/admin/category")
    public String category(Model model) {
        model.addAttribute("category", categoryService.getAllCategories());
        return "admin/category/index";
    }
    @GetMapping("/admin/category/create")
    public String createCategory(Model model) {
        model.addAttribute("categoryCreateDto", new Category());
        return "admin/category/create";
    }
    @PostMapping("/admin/category/create")
    public String createCategory(@ModelAttribute("categoryCreateDto") CategoryCreateDto categoryCreateDto) {
        categoryService.addCategory(categoryCreateDto);
        return "redirect:/admin/category";
    }
}
