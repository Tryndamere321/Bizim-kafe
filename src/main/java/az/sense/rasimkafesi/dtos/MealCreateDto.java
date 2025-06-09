package az.sense.rasimkafesi.dtos;

import lombok.Data;

@Data
public class MealCreateDto {
    private String name;
    private String description;
    private String photoUrl;
    private Double price;
    private Long categoryId;
}
