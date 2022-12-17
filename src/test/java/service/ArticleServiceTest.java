package service;

import entity.ArticleEntity;
import entity.UserEntity;
import newspaper_main.AssertsTools;
import newspaper_main.HomePageNewsPaperApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
@ContextConfiguration(initializers = {ArticleServiceTest.Initializer.class})
@Testcontainers
public class ArticleServiceTest {

    @Autowired
    AssertsTools assertsTools;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("articles")
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

    @Test
    @Transactional
    public void testAddArticle(ApplicationContext ctx){
        UserService userService = (UserService) ctx.getBean("userService");
        UserEntity user = userService.newUser("user@mail.ru", "test", "first name", "last_name", "ROLE_USER");
        userService.save(user);
        ArticleService articleService = (ArticleService) ctx.getBean("articleService");
        ArticleEntity article1 = articleService.newArticle("title 1", "src/main/resources/image1.png", "Description 1", user);
        Calendar date = new GregorianCalendar();
        date.add(Calendar.DAY_OF_YEAR, -2);
        ArticleEntity article2 = articleService.newArticleWithDate("title 2", "image", "Description 2", user, date.getTime());
        articleService.save(article1);
        articleService.save(article2);
        List<ArticleEntity> expected = Arrays.asList(article1, article2);
        List<ArticleEntity> result = Arrays.asList(articleService.findById(article1.getId()), articleService.findById(article2.getId()));
        Assertions.assertTrue(assertsTools.compareArticles(expected,result));
    }

}