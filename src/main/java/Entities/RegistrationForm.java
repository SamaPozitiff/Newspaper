package Entities;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public UserEntity toUser(PasswordEncoder passwordEncoder){
        return new UserEntity(email, passwordEncoder.encode(password), firstName, lastName);
    }
}
