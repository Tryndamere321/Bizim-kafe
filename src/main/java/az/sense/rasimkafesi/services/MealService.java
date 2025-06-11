package az.sense.rasimkafesi.services;

import az.sense.rasimkafesi.dtos.mealDtos.MealCreateDto;
import az.sense.rasimkafesi.dtos.mealDtos.MealDto;
import az.sense.rasimkafesi.dtos.mealDtos.MealUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MealService {
    List<MealDto> getAllMeals();
    boolean addMeal(MealCreateDto mealCreateDto, MultipartFile image);
    void deleteMeal(Long id);
    boolean updateMeal(MealUpdateDto mealUpdateDto,Long id, MultipartFile image);
    MealUpdateDto findUpdateMeal(Long id);
}
