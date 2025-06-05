package az.sense.rasimkafesi.dtos;

import lombok.Data;

@Data
public class MealDto {
    private Long id;
    private String name;
    private String description;
    private String photoUrl;
    private Double price;
}
