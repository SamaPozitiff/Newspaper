package com.example.homepagenewspaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
        "com.example.homepagenewspaper", "Repositories", "Entities"})
public class HomePageNewsPaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomePageNewsPaperApplication.class, args);

    }

}
