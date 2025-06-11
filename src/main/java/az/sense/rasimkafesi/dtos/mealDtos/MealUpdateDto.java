package az.sense.rasimkafesi.dtos.mealDtos;

import lombok.Data;

@Data
public class MealUpdateDto {
    private Long id;
    private String name;
    private String description;
    private String photoUrl;
    private Double price;
    private Long categoryId;
}
