package Repositories;

import Entities.NewspaperArticleEntity;
import com.example.homepagenewspaper.HomePageNewsPaperApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.*;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
public class ArticlesTest {

    @Test
    public void testGetArticlesFor24H(ApplicationContext ctx){
        Calendar date = new GregorianCalendar();
        date.add(Calendar.DAY_OF_YEAR, -2);
        Calendar date2 = new GregorianCalendar();
        date2.add(Calendar.HOUR, -1);
        Calendar date3 = new GregorianCalendar();
        date3.add(Calendar.DAY_OF_YEAR, +2);
        NewspaperArticleEntity article1 = new NewspaperArticleEntity(1L, "заголовок1", null, "бла бла бла", null, date2.getTime());
        NewspaperArticleEntity article2 = new NewspaperArticleEntity(2L, "заголовок2", null, "бла бла бла", null, date.getTime());
        NewspaperArticleEntity article3 = new NewspaperArticleEntity(3L, "заголовок3", null, "бла бла бла", null);
        NewspaperArticleEntity article4 = new NewspaperArticleEntity(4L, "заголовок4", null, "бла бла бла", null, date3.getTime());
        NewspaperArticleRepository repository = (NewspaperArticleRepository) ctx.getBean("newspaperArticleRepository");
        repository.save(article1);
        repository.save(article2);
        repository.save(article3);
        repository.save(article4);
        List<NewspaperArticleEntity> expect = Arrays.asList(article3,article1);
        List<NewspaperArticleEntity> result = repository.findAllArticlesForLast24Hours();

        Assertions.assertTrue(compareArticles(expect, result));
    }


    private boolean compareArticles(List<NewspaperArticleEntity> expect, List<NewspaperArticleEntity> result){
        if (expect.size()!=result.size()){
            return false;
        }
        for (int i = 0; i < expect.size(); i++){
            if (expect.get(i).getLikes()!= result.get(i).getLikes()) return false;
            if (!Objects.equals(expect.get(i).getId(), result.get(i).getId())) return false;
            if (!Objects.equals(expect.get(i).getTitle(), result.get(i).getTitle())) return false;
            if (!Objects.equals(expect.get(i).getImage(), result.get(i).getImage())) return false;
            if (!Objects.equals(expect.get(i).getDescription(), result.get(i).getDescription())) return false;
            if (!Objects.equals(expect.get(i).getAuthor(), result.get(i).getAuthor())) return false;
            if (!Objects.equals(expect.get(i).getPublicationDate(), result.get(i).getPublicationDate())) return false;

        }
        return true;
    }
}
