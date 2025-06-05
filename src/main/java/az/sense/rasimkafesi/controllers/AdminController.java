package az.sense.rasimkafesi.controllers;

import az.sense.rasimkafesi.services.MealService;
import az.sense.rasimkafesi.services.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private final MealService mealService;
    private final WorkerService workerService;
    public AdminController(MealService mealService, WorkerService workerService) {
        this.mealService = mealService;
        this.workerService = workerService;
    }
    @GetMapping("/admin")
    public String dashboard(Model model) {
        model.addAttribute("meal",mealService.getAllMeals().stream().count());
        model.addAttribute("worker",workerService.getAllWorkers().stream().count());
        return "admin/index";
    }
}
