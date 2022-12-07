package entities;


import org.junit.jupiter.api.BeforeAll;
import services.ArticleService;
import com.example.homepagenewspaper.HomePageNewsPaperApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;


@SpringBootTest(classes = HomePageNewsPaperApplication.class)
public class ArticleTest {

//    @BeforeAll
//    public void makeEntities(ArticleService articleService, Comment){
//
//    }
//
//    @Test
//    public void addComment(ApplicationContext ctx){
//        CommentEntity commentEntity = new CommentEntity("100 из 10", null, null);
//        ArticleEntity article = new ArticleEntity("шла саша по шоссе", null, "и сосала сушку", null);
//        ArticleService articleService = (ArticleService) ctx.getBean("articleService");
//        articleService.addComment(article, commentEntity);
//        articleService.save(article);
//        article = articleService.findById(article.getId());
//        CommentEntity comment2 = article.getComments().get(0);
//        Assertions.assertTrue(comment2.getDescription().equals(commentEntity.getDescription()));
//    }



}
