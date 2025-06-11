package az.sense.rasimkafesi.controllers;

import az.sense.rasimkafesi.dtos.userDto.UserRegisterDto;
import az.sense.rasimkafesi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
    @GetMapping("/register")
    public String register(){
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDto userRegisterDto){
        userService.registerUser(userRegisterDto);
        return "redirect:/login";
    }
}
