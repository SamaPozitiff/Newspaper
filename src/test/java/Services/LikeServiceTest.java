package Services;

import com.example.homepagenewspaper.HomePageNewsPaperApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
public class LikeServiceTest {
    @Test
    public void likeTest(ApplicationContext ctx){
        LikeService likeService = (LikeService) ctx.getBean("likeService");
        Assertions.assertEquals(0,likeService.getAmountLikesFromArticle(999));
    }


    @Test
    public void isUserLikeThis(ApplicationContext ctx){
        LikeService likeService = (LikeService) ctx.getBean("likeService");
        Assertions.assertEquals(false, likeService.isUserLikeThisArticle(999L,555L));
    }


}
