package Repositories;

import Entities.NewspaperArticleEntity;
import com.example.homepagenewspaper.HomePageNewsPaperApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
public class CommentsTests {

    @Test
    public void commentsTests(ApplicationContext ctx){
        //NewspaperArticleEntity newspaperArticle = new NewspaperArticleEntity(5L, "")
    }
}
