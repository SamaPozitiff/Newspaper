package controllers;

import com.example.homepagenewspaper.RegistrationForm;
import entities.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repositories.UserRepository;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(){
        return "registration";
    }

    @PostMapping
    public String processRegistration(@RequestParam String email, String password, String firstName, String lastName){
        userRepository.save(new RegistrationForm(email, password, firstName, lastName).toUser(passwordEncoder));
        return "redirect:/login";
    }

}
