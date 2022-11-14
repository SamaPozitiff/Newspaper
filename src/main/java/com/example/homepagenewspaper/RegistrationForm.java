package com.example.homepagenewspaper;

import entities.UserEntity;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String email;
    private String password;
    private String firstName;
    private String secondName;

    public UserEntity toUser(PasswordEncoder passwordEncoder){
        return new UserEntity(email, passwordEncoder.encode(password),
                firstName, secondName);
    }
}
