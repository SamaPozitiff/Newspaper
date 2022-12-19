package jwt.auth;

import entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
public class RegistrationForm {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    /*
    создает и возвращает сущность пользователь с зашифрованным паролем
     */
    public UserEntity toUser(PasswordEncoder passwordEncoder){
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        return user;
    }
}
