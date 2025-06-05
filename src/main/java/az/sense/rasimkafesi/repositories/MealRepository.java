package az.sense.rasimkafesi.repositories;

import az.sense.rasimkafesi.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Meal findMealByName(String name);
}
