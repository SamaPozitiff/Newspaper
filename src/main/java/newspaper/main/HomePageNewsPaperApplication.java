package newspaper.main;

import entity.ArticleEntity;
import entity.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import service.*;


@SpringBootApplication(scanBasePackages={
        "newspaper.main", "repository", "entity", "controller", "service", "jwt.auth", "mapper","configuration", "facade"})
public class HomePageNewsPaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomePageNewsPaperApplication.class, args);

    }
    /**
     Заполняет базу данных при запуске приложения

     */

        @Bean
    public CommandLineRunner runner(PasswordEncoder encoder, ArticleService articleService, UserService userService, CommentService commentService, LikeService likeService){
        return args -> {
            for(int i = 0; i < 10; i++){
                UserEntity user = userService.newUser( "user" + i + "@mail.ru", encoder.encode("password"),
                        "user" + i, "lastname" + i, "ROLE_USER");
                userService.save(user);
                ArticleEntity article = articleService.newArticle("" + i + "котяток", "classes/image1.png", "" + i + "часов назад было обнаружено, что котики сладко мурчат", user);
                articleService.save(article);
                commentService.save(commentService.newComment("1" + i + " котяток из 10", user, article));
                likeService.like(article,user);
            }
        };
    }


}
