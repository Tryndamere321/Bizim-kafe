package az.sense.rasimkafesi.controllers;

import az.sense.rasimkafesi.services.MealService;
import az.sense.rasimkafesi.services.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private final MealService mealService;
    private final WorkerService workerService;
    public IndexController(MealService mealService, WorkerService workerService) {
        this.mealService = mealService;
        this.workerService = workerService;
    }
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("meal",mealService.getAllMeals());
        model.addAttribute("worker",workerService.getAllWorkers());
        return "index";
    }
}
