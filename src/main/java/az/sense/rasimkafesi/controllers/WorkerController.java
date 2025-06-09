package az.sense.rasimkafesi.controllers;

import az.sense.rasimkafesi.dtos.MealUpdateDto;
import az.sense.rasimkafesi.dtos.workerDtos.WorkerCreateDto;
import az.sense.rasimkafesi.dtos.workerDtos.WorkerUpdateDto;
import az.sense.rasimkafesi.models.Worker;
import az.sense.rasimkafesi.services.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class WorkerController {
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }
    @GetMapping("/admin/worker")
    public String admin(Model model){
        model.addAttribute("worker",workerService.getAllWorkers());
        return "admin/worker/index";
    }
    @GetMapping("/admin/worker/create")
    public String create(Model model){
        model.addAttribute("workerCreateDto",new Worker());
        return "admin/worker/create";
    }
    @PostMapping("/admin/worker/create")
    public String create(@ModelAttribute("workerCreateDto")WorkerCreateDto workerCreateDto, MultipartFile image){
        workerService.createWorker(workerCreateDto,image);
        return "redirect:/admin/worker";
    }
    @PostMapping("/admin/worker/delete/{id}")
    public String delete(@PathVariable Long id){
        workerService.deleteWorker(id);
        return "redirect:/admin/worker";
    }
    @GetMapping("/admin/worker/update/{id}")
    public String home(Model model,@PathVariable Long id){
        model.addAttribute("workerUpdateDto",workerService.findWorkerById(id));
        return "admin/worker/update";
    }
    @PostMapping("/admin/worker/update/{id}")
    public String home(@PathVariable Long id, @ModelAttribute("workerUpdateDto") WorkerUpdateDto workerUpdateDto, MultipartFile image){
        workerService.updateWorker(workerUpdateDto,id,image);
        return "redirect:/admin/worker";
    }
}
