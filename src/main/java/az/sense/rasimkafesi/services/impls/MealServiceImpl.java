package az.sense.rasimkafesi.services.impls;

import az.sense.rasimkafesi.dtos.mealDtos.MealCreateDto;
import az.sense.rasimkafesi.dtos.mealDtos.MealDto;
import az.sense.rasimkafesi.dtos.mealDtos.MealUpdateDto;
import az.sense.rasimkafesi.models.Meal;
import az.sense.rasimkafesi.repositories.MealRepository;
import az.sense.rasimkafesi.services.MealService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class MealServiceImpl implements MealService {
    private final ModelMapper modelMapper;
    private final MealRepository mealRepository;
    private final Cloudinary cloudinary;

    public MealServiceImpl(ModelMapper modelMapper, MealRepository mealRepository, Cloudinary cloudinary) {
        this.modelMapper = modelMapper;
        this.mealRepository = mealRepository;
        this.cloudinary = cloudinary;
    }

    @Override
    public List<MealDto> getAllMeals() {
        List<Meal> meals = mealRepository.findAll();
        return meals.stream().map(x -> modelMapper.map(x, MealDto.class)).toList();
    }

    @Override
    public boolean addMeal(MealCreateDto mealCreateDto, MultipartFile image) {
        Meal findmeal = mealRepository.findMealByName(mealCreateDto.getName());
        if (findmeal != null) {
            return false;
        }
        try {
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            String photoUrl = (String) uploadResult.get("url");

            Meal meal = new Meal();
            meal.setPhotoUrl(photoUrl);
            meal.setName(mealCreateDto.getName());
            meal.setDescription(mealCreateDto.getDescription());
            meal.setPrice(mealCreateDto.getPrice());
            mealRepository.save(meal);
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

    @Override
    public boolean updateMeal(MealUpdateDto mealUpdateDto, Long id, MultipartFile image) {
        Meal findMeal = mealRepository.findById(id).orElseThrow();
        try {
            if (image.isEmpty()) {
                findMeal.setPhotoUrl(mealUpdateDto.getPhotoUrl());
            } else {
                Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
                String photoUrl = (String) uploadResult.get("url");
                findMeal.setPhotoUrl(photoUrl);
            }

            findMeal.setName(mealUpdateDto.getName());
            findMeal.setPrice(mealUpdateDto.getPrice());
            findMeal.setDescription(mealUpdateDto.getDescription());
            mealRepository.save(findMeal);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public MealUpdateDto findUpdateMeal(Long id) {
       Meal meal =mealRepository.findById(id).orElseThrow();
       return modelMapper.map(meal,MealUpdateDto.class);
    }
}
