package repositories;

import entities.ArticleEntity;
import services.ArticleService;
import com.example.homepagenewspaper.HomePageNewsPaperApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.*;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
public class ArticlesTest {
//
//    @Test
//    public void testGetArticlesFor24H(ApplicationContext ctx){
//        Calendar date = new GregorianCalendar();
//        date.add(Calendar.DAY_OF_YEAR, -2);
//        Calendar date2 = new GregorianCalendar();
//        date2.add(Calendar.HOUR, -1);
//        Calendar date3 = new GregorianCalendar();
//        date3.add(Calendar.DAY_OF_YEAR, +2);
//        ArticleEntity article1 = new ArticleEntity(1L, "заголовок1", null, "бла бла бла", null, date2.getTime());
//        ArticleEntity article2 = new ArticleEntity(2L, "заголовок2", null, "бла бла бла", null, date.getTime());
//        ArticleEntity article3 = new ArticleEntity(3L, "заголовок3", null, "бла бла бла", null);
//        ArticleEntity article4 = new ArticleEntity(4L, "заголовок4", null, "бла бла бла", null, date3.getTime());
//        ArticleService service = (ArticleService) ctx.getBean("articleService");
//        service.save(article1);
//        service.save(article2);
//        service.save(article3);
//        service.save(article4);
//        List<ArticleEntity> expect = Arrays.asList(article3,article1);
//        List<ArticleEntity> result = service.getAllArticlesFor24Hours();
//
//        Assertions.assertTrue(compareArticles(expect, result));
//    }
//
//
//    private boolean compareArticles(List<ArticleEntity> expect, List<ArticleEntity> result){
//        if (expect.size()!=result.size()){
//            return false;
//        }
//        for (int i = 0; i < expect.size(); i++){
//            if (!Objects.equals(expect.get(i).getId(), result.get(i).getId())) return false;
//            if (!Objects.equals(expect.get(i).getTitle(), result.get(i).getTitle())) return false;
//            if (!Objects.equals(expect.get(i).getImage(), result.get(i).getImage())) return false;
//            if (!Objects.equals(expect.get(i).getDescription(), result.get(i).getDescription())) return false;
//            if (!Objects.equals(expect.get(i).getAuthor(), result.get(i).getAuthor())) return false;
//            if (!Objects.equals(expect.get(i).getPublicationDate(), result.get(i).getPublicationDate())) return false;
//
//        }
//        return true;
//    }
//
//    @Test
//    public void testGet3LastArticles(ApplicationContext ctx){
//        GregorianCalendar date1 = new GregorianCalendar();
//        date1.add(Calendar.HOUR, -1);
//        GregorianCalendar date2 = new GregorianCalendar();
//        date2.add(GregorianCalendar.HOUR, -2);
//        GregorianCalendar date3 = new GregorianCalendar();
//        date3.add(Calendar.HOUR, -3);
//        ArticleRepository repository = (ArticleRepository) ctx.getBean("articleRepository");
//        ArticleEntity article1 = new ArticleEntity("Было 4 кокоса", null, "один из них упал, осталось 3 кокоса", null);
//        ArticleEntity article2 = new ArticleEntity("Было 3 кокоса", null, "один из них упал, осталось 2 кокоса", null, date1.getTime());
//        ArticleEntity article3 = new ArticleEntity("Было 2 кокоса", null, "один из них упал, осталcя 1 кокос", null, date2.getTime());
//        ArticleEntity article4 = new ArticleEntity("Был 1 кокос", null, "и он упал, нет больше кокосов Т_Т", null, date3.getTime());
//        repository.save(article1);
//        repository.save(article2);
//        repository.save(article3);
//        repository.save(article4);
//        List<ArticleEntity> expect = Arrays.asList(article1,article2,article3);
//        List<ArticleEntity> result = repository.findLast3Articles();
//        Assertions.assertTrue(compareArticles(expect,result));
//    }
}
