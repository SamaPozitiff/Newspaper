package service;

import newspaper_main.AssertsTools;
import newspaper_main.HomePageNewsPaperApplication;
import entity.ArticleEntity;
import entity.CommentEntity;
import entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
import repository.CommentRepository;


import java.util.*;
import java.util.stream.Stream;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
@ContextConfiguration(initializers = {CommentServiceTest.Initializer.class})
@Testcontainers
public class CommentServiceTest {
    @Autowired    CommentService commentService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    AssertsTools assertsTools;


    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("comments")
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
    public void save(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        CommentEntity comment = commentService.newComment("description", user, article);
        commentService.save(comment);
        Assertions.assertTrue(assertsTools.compareComment(comment, commentService.findById(comment.getId())));

    }
    @Transactional
    @Test
    public void findPagedCommentsOfArticle(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        List<CommentEntity> page0 = new ArrayList<>();
        List<CommentEntity> page1 = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            CommentEntity comment = commentService.newComment("description" + i, user, article);
            commentService.save(comment);
            if(i<2){
                page1.add(0, comment);
            }else {
                page0.add(0, comment);
            }
        }
        Assertions.assertTrue(assertsTools.compareListOfComments(page0, commentService.findPagedCommentsOfArticle(0, article.getId())));
        Assertions.assertTrue(assertsTools.compareListOfComments(page1, commentService.findPagedCommentsOfArticle(1, article.getId())));
    }
    @Transactional
    @Test
    public void delete(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("tit;e", "image", "description", user);
        articleService.save(article);
        CommentEntity comment = commentService.newComment("description", user, article);
        commentService.save(comment);
        Assertions.assertTrue(assertsTools.compareComment(comment, commentService.findById(comment.getId())));
        commentService.delete(comment.getId());
        Assertions.assertFalse(assertsTools.compareComment(comment, commentService.findById(comment.getId())));
    }
    @Transactional
    @Test
    public void findAllComments(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("tit;e", "image", "description", user);
        articleService.save(article);
        List<CommentEntity> expect = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            CommentEntity comment = commentService.newComment("description" + i, user, article);
            expect.add(0,comment);
            commentService.save(comment);
        }
        Assertions.assertTrue(assertsTools.compareListOfComments(expect, commentService.findAllCommentsOfArticle(article.getId())));
    }
    @Transactional
    @Test
    public void newComment(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        ArticleEntity article = articleService.newArticle("tit;e", "image", "description", user);
        CommentEntity expect = new CommentEntity();
        expect.setArticle(article);
        expect.setDescription("description");
        expect.setUser(user);
        CommentEntity result = commentService.newComment("description", user, article);
        Assertions.assertEquals(expect.getDescription(), result.getDescription());
        Assertions.assertTrue(assertsTools.compareArticle(expect.getArticle(), result.getArticle()));
        Assertions.assertTrue(assertsTools.compareUser(expect.getUser(), result.getUser()));    }

    @Transactional
    @Test
    public void newCommentWithDate(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        ArticleEntity article = articleService.newArticle("tit;e", "image", "description", user);
        CommentEntity expect = new CommentEntity();
        Calendar date = new GregorianCalendar();
        date.add(Calendar.DAY_OF_YEAR, -2);
        expect.setUser(user);
        expect.setPublicationDate(date.getTime());
        expect.setDescription("description");
        expect.setArticle(article);
        CommentEntity result = commentService.newCommentWithDate("description", user, article, date.getTime());
        Assertions.assertEquals(expect.getDescription(), result.getDescription());
        Assertions.assertTrue(assertsTools.compareUser(expect.getUser(), result.getUser()));
        Assertions.assertTrue(assertsTools.compareArticle(expect.getArticle(), result.getArticle()));
        Assertions.assertEquals(expect.getPublicationDate(), result.getPublicationDate());
    }
}
