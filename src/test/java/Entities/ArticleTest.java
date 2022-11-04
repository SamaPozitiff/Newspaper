package Entities;


import Repositories.NewspaperArticleRepository;
import com.example.homepagenewspaper.HomePageNewsPaperApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;


@SpringBootTest(classes = HomePageNewsPaperApplication.class)
public class ArticleTest {

    @Test
    public void addComment(ApplicationContext ctx){
        CommentEntity commentEntity = new CommentEntity("100 из 10", null);
        NewspaperArticleEntity article = new NewspaperArticleEntity("шла саша по шоссе", null, "и сосала сушку", null);
        NewspaperArticleRepository newspaperArticleRepository = (NewspaperArticleRepository) ctx.getBean("newspaperArticleRepository");
        article.addComment(commentEntity);
        newspaperArticleRepository.save(article);
        article = newspaperArticleRepository.findById(article.getId()).get();
        CommentEntity comment2 = article.getComments().get(0);
        Assertions.assertTrue(comment2.getDescription().equals(commentEntity.getDescription()));
    }



}
