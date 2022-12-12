package services;


import com.example.homepagenewspaper.AssertsTools;
import com.example.homepagenewspaper.HomePageNewsPaperApplication;
import entities.ArticleEntity;
import entities.UserEntity;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import services.ArticleService;
import services.UserService;

import java.util.*;


@SpringBootTest(classes = HomePageNewsPaperApplication.class)
public class ArticleServiceTest {
    @Autowired
    AssertsTools assertsTools;


    @Test
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
