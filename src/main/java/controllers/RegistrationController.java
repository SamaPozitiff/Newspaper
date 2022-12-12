package controllers;

import JwtAuth.RegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    /**
    регистрация нового пользователя
    @param email - электронная почта
     @param password - пароль
     @param firstName - имя
     @param lastName - фамилия
     @param role - роль
     */
    @PostMapping
    public String processRegistration(@RequestParam String email, String password, String firstName, String lastName, String role){
        userRepository.save(new RegistrationForm(email, password, firstName, lastName, role).toUser(passwordEncoder));
        return "redirect:/login";
    }

}
