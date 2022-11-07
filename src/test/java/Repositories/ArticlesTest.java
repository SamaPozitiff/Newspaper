package Repositories;

import Entities.NewspaperArticleEntity;
import Services.ArticleService;
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
        ArticleService service = (ArticleService) ctx.getBean("articleService");
        service.save(article1);
        service.save(article2);
        service.save(article3);
        service.save(article4);
        List<NewspaperArticleEntity> expect = Arrays.asList(article3,article1);
        List<NewspaperArticleEntity> result = service.getAllArticlesFor24Hours();

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

    @Test
    public void testGet3LastArticles(ApplicationContext ctx){
        GregorianCalendar date1 = new GregorianCalendar();
        date1.add(Calendar.HOUR, -1);
        GregorianCalendar date2 = new GregorianCalendar();
        date2.add(GregorianCalendar.HOUR, -2);
        GregorianCalendar date3 = new GregorianCalendar();
        date3.add(Calendar.HOUR, -3);
        NewspaperArticleRepository repository = (NewspaperArticleRepository) ctx.getBean("newspaperArticleRepository");
        NewspaperArticleEntity article1 = new NewspaperArticleEntity("Было 4 кокоса", null, "один из них упал, осталось 3 кокоса", null);
        NewspaperArticleEntity article2 = new NewspaperArticleEntity("Было 3 кокоса", null, "один из них упал, осталось 2 кокоса", null, date1.getTime());
        NewspaperArticleEntity article3 = new NewspaperArticleEntity("Было 2 кокоса", null, "один из них упал, осталcя 1 кокос", null, date2.getTime());
        NewspaperArticleEntity article4 = new NewspaperArticleEntity("Был 1 кокос", null, "и он упал, нет больше кокосов Т_Т", null, date3.getTime());
        repository.save(article1);
        repository.save(article2);
        repository.save(article3);
        repository.save(article4);
        List<NewspaperArticleEntity> expect = Arrays.asList(article1,article2,article3);
        List<NewspaperArticleEntity> result = repository.findLast3Articles();
        Assertions.assertTrue(compareArticles(expect,result));
    }
}
