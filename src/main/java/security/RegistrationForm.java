package security;

import entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
public class RegistrationForm {
    private String email;
    private String password;
    private String firstName;
    private String secondName;

    private String role;

    public UserEntity toUser(PasswordEncoder passwordEncoder){
        return new UserEntity(email, passwordEncoder.encode(password),
                firstName, secondName, role);
    }
}
