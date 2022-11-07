package com.example.homepagenewspaper;

import Entities.ArticleEntity;
import Entities.CommentEntity;
import Repositories.CommentRepository;
import Repositories.ArticleRepository;
import Repositories.UserRepository;
import Entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
class HomePageNewsPaperApplicationTests {
    UserEntity user = new UserEntity(1L, "Purr", "Meow", "Любитель", "Котиков", "purr@gmail.com");
    ArticleEntity testArticle = new ArticleEntity(2L, "Статья про " + 0 +" котиков", null, "" + 0 + "шерстяных комочков сладко мурчат", user);

    @Test
    void contextLoads() {
    }

    @Test
    public void testAddArticles(ApplicationContext ctx){

        ArticleRepository articleRepository = (ArticleRepository) ctx.getBean("articleRepository");
        for (int i = 0; i < 5; i++){
            ArticleEntity article = new ArticleEntity(Long.valueOf(i), "Статья про " + i +" котиков", null, "" + i + "шерстяных комочков сладко мурчат", null);


            articleRepository.save(article);
        }
    }



    @Test
    public void testGet(){

    }

    @Test
    @Bean
    public void testAddUser(ApplicationContext ctx){
        for (int i = 0; i<3; i++){
            UserEntity userEntity = new UserEntity(Long.valueOf(i), "пупа" + i, "лупа" + i, "пупа" + i, "лупа" + i, "лупа"+i+"@gmail.com");
            UserRepository userRepository = (UserRepository)ctx.getBean("userRepository");
            userRepository.save(userEntity);
        }
    }

    @Test
    public void testAddComment(ApplicationContext ctx){
        UserRepository userRepository = (UserRepository) ctx.getBean("userRepository");
        userRepository.save(user);
        for (int i = 10; i < 15; i++) {
            CommentEntity comment = new CommentEntity(Long.valueOf(i), "" + i + " котят из 10", user);
            CommentRepository commentRepository = (CommentRepository) ctx.getBean("commentRepository");
            commentRepository.save(comment);
        }
    }

}
