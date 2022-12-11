package com.example.homepagenewspaper;

import entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.ArticleRepository;
import repositories.CommentRepository;
import repositories.LikeRepository;
import repositories.UserRepository;
import services.ArticleService;
import services.CommentService;

import javax.swing.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication(scanBasePackages={
        "com.example.homepagenewspaper", "repositories", "entities", "controllers", "services", "security","mappers"})
public class HomePageNewsPaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomePageNewsPaperApplication.class, args);

    }
    /**
     Заполняет базу данных при запуске приложения

     */
    @Bean
    public CommandLineRunner runner(PasswordEncoder encoder, ArticleService articleService, UserRepository userRepository, CommentService commentService, LikeRepository likeRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                for(int i = 0; i < 10; i++){
                    UserEntity user = new UserEntity( "user" + i + "@mail.ru", encoder.encode("password"),
                            "user" + i, "lastname" + i, "ROLE_USER");
                    userRepository.save(user);
                    ArticleEntity article = articleService.newArticle("" + i + "котяток", "src/resources/image1.png", "" + i + "часов назад было обнаружено, что котики сладко мурчат", user);
                    articleService.save(article);
                    commentService.save(commentService.newComment("1" + i + " котяток из 10", user, article));
                    likeRepository.save(new LikeEntity(article, user));
                }
            }
        };
    }

}
