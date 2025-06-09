package az.sense.rasimkafesi.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String photoUrl;
    private Double price;
    @ManyToOne
    private Category category;
}
