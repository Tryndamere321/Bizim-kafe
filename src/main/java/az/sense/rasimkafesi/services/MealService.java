package az.sense.rasimkafesi.services;

import az.sense.rasimkafesi.dtos.MealCreateDto;
import az.sense.rasimkafesi.dtos.MealDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MealService {
    List<MealDto> getAllMeals();
    boolean addMeal(MealCreateDto mealCreateDto, MultipartFile image);
    void deleteMeal(Long id);
}
