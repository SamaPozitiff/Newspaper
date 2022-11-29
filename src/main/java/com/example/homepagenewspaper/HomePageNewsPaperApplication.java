package com.example.homepagenewspaper;

import entities.ArticleEntity;
import entities.CommentEntity;
import entities.LikeEntity;
import entities.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.ArticleRepository;
import repositories.CommentRepository;
import repositories.LikeRepository;
import repositories.UserRepository;

import javax.swing.*;


@SpringBootApplication(scanBasePackages={
        "com.example.homepagenewspaper", "repositories", "entities", "controllers", "services", "security"})
public class HomePageNewsPaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomePageNewsPaperApplication.class, args);

    }

    @Bean
    public CommandLineRunner runner(PasswordEncoder encoder, ArticleRepository articleRepository, UserRepository userRepository, CommentRepository commentRepository, LikeRepository likeRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                for(int i = 0; i < 10; i++){
                    UserEntity user = new UserEntity( "user" + i + "@mail.ru", encoder.encode("password"),
                            "user" + i, "lastname" + i, "ROLE_USER");
                    userRepository.save(user);
                    ArticleEntity article = new ArticleEntity("" + i + "котяток", "src/resources/image" + i + ".png", "" + i + "часов назад было обнаружено, что котики сладко мурчат", user);
                    articleRepository.save(article);
                    commentRepository.save(new CommentEntity("1" + i + " из 10", user, article));
                    likeRepository.save(new LikeEntity(article, user));
                }
            }
        };
    }

}
