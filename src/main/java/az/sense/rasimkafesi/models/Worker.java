package az.sense.rasimkafesi.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "workers")
@Data
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String position;
    private String information;
}
