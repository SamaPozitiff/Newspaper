package JwtAuth;

import entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import services.UserService;

@Data
@AllArgsConstructor
public class RegistrationForm {
    @Autowired
    UserService userService;
    private String email;
    private String password;
    private String firstName;
    private String secondName;
    private String role;
    /*
    создает и возвращает сущность пользователь с зашифрованным паролем
     */
    public UserEntity toUser(PasswordEncoder passwordEncoder){
        return userService.newUser(email, passwordEncoder.encode(password),
                firstName, secondName, role);
    }
}
