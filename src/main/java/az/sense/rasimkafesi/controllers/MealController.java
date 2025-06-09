package az.sense.rasimkafesi.controllers;

import az.sense.rasimkafesi.dtos.MealCreateDto;
import az.sense.rasimkafesi.dtos.MealUpdateDto;
import az.sense.rasimkafesi.models.Meal;
import az.sense.rasimkafesi.services.MealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MealController {
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }
    @GetMapping("/admin/meal")
    public String index(Model model){
        model.addAttribute("meal",mealService.getAllMeals());
        return "admin/meal/index";
    }
    @GetMapping("/admin/meal/create")
    public String create(Model model){
        model.addAttribute("mealCreateDto",new Meal());
        return "admin/meal/create";
    }
    @PostMapping("/admin/meal/create")
    public String create(@ModelAttribute("mealCreateDto")MealCreateDto mealCreateDto, MultipartFile image){
        mealService.addMeal(mealCreateDto,image);
        return "redirect:/admin/meal";
    }
    @PostMapping("/admin/meal/delete/{id}")
    public String delete(@PathVariable Long id){
        mealService.deleteMeal(id);
        return "redirect:/admin/meal";
    }
    @GetMapping("/admin/meal/update/{id}")
    public String home(Model model,@PathVariable Long id){
        model.addAttribute("mealUpdateDto",mealService.findUpdateMeal(id));
        return "admin/meal/update";
    }
    @PostMapping("/admin/meal/update/{id}")
    public String home(@PathVariable Long id, @ModelAttribute("mealUpdateDto")MealUpdateDto mealUpdateDto,MultipartFile image){
     mealService.updateMeal(mealUpdateDto,id,image);
     return "redirect:/admin/meal";
    }





}
