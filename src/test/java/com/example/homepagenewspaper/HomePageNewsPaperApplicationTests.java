package com.example.homepagenewspaper;

import Entities.CommentEntity;
import JPARepositories.CommentRepository;
import JPARepositories.NewspaperArticleRepository;
import JPARepositories.UserJPARepository;
import Entities.NewspaperArticleEntity;
import Entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class HomePageNewsPaperApplicationTests {
    UserEntity user = new UserEntity(1L, "Purr", "Meow", "Любитель", "Котиков", "purr@gmail.com");
    NewspaperArticleEntity testArticle = new NewspaperArticleEntity(2L, "Статья про " + 0 +" котиков", null, "" + 0 + "шерстяных комочков сладко мурчат", user);

    @Test
    void contextLoads() {
    }

    @Test
    public void testAddArticles(ApplicationContext ctx){

        NewspaperArticleRepository newspaperArticleRepository = (NewspaperArticleRepository) ctx.getBean("newspaperArticleRepository");
        for (int i = 0; i < 5; i++){
            NewspaperArticleEntity article = new NewspaperArticleEntity(Long.valueOf(i), "Статья про " + i +" котиков", null, "" + i + "шерстяных комочков сладко мурчат", user);


            newspaperArticleRepository.save(article);
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
            UserJPARepository userRepository = (UserJPARepository)ctx.getBean("userRepository");
            userRepository.save(userEntity);
        }
    }

    @Test
    public void testAddComment(ApplicationContext ctx){
        UserJPARepository userRepository = (UserJPARepository) ctx.getBean("userRepository");
        userRepository.save(user);
        for (int i = 10; i < 15; i++) {
            CommentEntity comment = new CommentEntity(Long.valueOf(i), "" + i + " котят из 10", user);
            CommentRepository commentRepository = (CommentRepository) ctx.getBean("commentRepository");
            commentRepository.save(comment);
        }
    }

}
