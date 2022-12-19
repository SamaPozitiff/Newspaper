package service;

import entity.ArticleEntity;
import entity.UserEntity;
import newspaper_main.HomePageNewsPaperApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
@ContextConfiguration(initializers = {PSQLContainer.Initializer.class})
@Testcontainers
public class ArticleServiceTest extends PSQLContainer{

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

        Assertions.assertTrue(compareArticles(expected,result));
    }

    public boolean compareArticles(List<ArticleEntity> expect, List<ArticleEntity> result){
        if (expect.size()!=result.size()){
            return false;
        }
        for (int i = 0; i < expect.size(); i++){
            if(!expect.get(i).equals(result.get(i))) return false;
        }
        return true;
    }

}