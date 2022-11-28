package com.example.homepagenewspaper;

import entities.UserEntity;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.UserRepository;

@SpringBootApplication(scanBasePackages={
        "com.example.homepagenewspaper", "repositories", "entities", "controllers", "services"})
public class HomePageNewsPaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomePageNewsPaperApplication.class, args);

    }

    @Bean
    public ApplicationRunner dataLoader(
            UserRepository repository, PasswordEncoder encoder){
        return args -> repository.save(
                new UserEntity( "inna@mail.ru", encoder.encode("password"),
                        "inna", "brazhenko", "ROLE_USER"));
    }

}
