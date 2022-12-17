package service;

import newspaper_main.AssertsTools;
import entity.ArticleEntity;
import entity.LikeEntity;
import entity.UserEntity;
import newspaper_main.HomePageNewsPaperApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import repository.LikeRepository;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
@ContextConfiguration(initializers = {LikeServiceTest.Initializer.class})
@Testcontainers
public class LikeServiceTest {

    @Autowired
    LikeService likeService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    AssertsTools assertsTools;
    @Autowired
    LikeRepository likeRepository;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("likes")
            .withUsername("samapozitiff")
            .withPassword("DocGironimo248");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.liquibase.enabled=true"
            ).applyTo(applicationContext.getEnvironment());
        }
    }

    @Transactional
    @Test
    public void like(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        LikeEntity like = likeService.newLike(article, user);
        likeService.like(article, user);
        Assertions.assertTrue(assertsTools.compareArticle(like.getArticle(), likeService.getLike(article, user).getArticle()));
        Assertions.assertTrue(assertsTools.compareUser(like.getUser(), likeService.getLike(article, user).getUser()));

    }

    @Transactional
    @Test
    public void dislike(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        LikeEntity like = likeService.newLike(article, user);
        likeService.like(article, user);
        Assertions.assertTrue(assertsTools.compareArticle(like.getArticle(), likeService.getLike(article, user).getArticle()));
        Assertions.assertTrue(assertsTools.compareUser(like.getUser(), likeService.getLike(article, user).getUser()));
        likeService.dislike(article, user);
        Assertions.assertTrue(likeService.getLike(article, user) == null);
    }

    @Transactional
    @Test
    public void newLike(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        LikeEntity expect = new LikeEntity();
        expect.setArticle(article);
        expect.setUser(user);
        LikeEntity result = likeService.newLike(article, user);
        Assertions.assertTrue(assertsTools.compareArticle(expect.getArticle(), result.getArticle()));
        Assertions.assertTrue(assertsTools.compareUser(expect.getUser(), result.getUser()));

    }

    @Transactional
    @Test
    public void getAmountLikesFromArticles(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        for(int i = 0; i < 5; i++){
            UserEntity userForLike = userService.newUser("email" + i + "@mail.ru", "password", "firstname", "lastname", "ROLE_USER");
            userService.save(userForLike);
            likeService.like(article, userForLike);
        }
        Assertions.assertEquals(5L, likeService.getAmountLikesFromArticle(article.getId()));
    }

    @Transactional
    @Test
    public void isUserLikeThisArticle(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        likeService.like(article, user);
        Assertions.assertTrue(likeService.isUserLikeThisArticle(article, user));
    }

    @Transactional
    @Test
    public void getLike(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        LikeEntity expect = new LikeEntity();
        expect.setUser(user);
        expect.setArticle(article);
        likeService.like(article, user);
        Assertions.assertTrue(assertsTools.compareArticle(expect.getArticle(), likeService.getLike(article,user).getArticle()));
        Assertions.assertTrue(assertsTools.compareUser(expect.getUser(), likeService.getLike(article,user).getUser()));

    }
}
