package services;

import com.example.homepagenewspaper.HomePageNewsPaperApplication;
import entities.ArticleEntity;
import entities.CommentEntity;
import entities.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.*;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
public class CommentServiceTest {

    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;

    @Test
    public void getCommentsTest(){
        UserEntity user1  = new UserEntity("qwerty", "", "??????", "ivan@gmail.com");
        userService.save(user1);
        ArticleEntity article1 = new ArticleEntity("hello", null, "world", user1);
        articleService.save(article1);
        GregorianCalendar date = new GregorianCalendar();
        List expect = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            date.add(Calendar.HOUR, -i);
            CommentEntity comment = new CommentEntity("" + i + " из 10", user1, date.getTime(), article1);
            commentService.save(comment);
            expect.add(comment);
        }
            List result = commentService.getPageCommentsOfArticle(article1.getId());
            Assertions.assertTrue(compareArticles(expect, result));


    }

    private boolean compareArticles(List<CommentEntity> expect, List<CommentEntity> result) {
        if (expect.size() != result.size()) {
            return false;
        }
        for (int i = 0; i < expect.size(); i++) {
            if (!Objects.equals(expect.get(i).getId(), result.get(i).getId())) return false;
            if (!Objects.equals(expect.get(i).getDescription(), result.get(i).getDescription())) return false;
            if (!Objects.equals(expect.get(i).getArticle(), result.get(i).getArticle())) return false;
            if (!Objects.equals(expect.get(i).getUser(), result.get(i).getUser())) return false;
            if (!Objects.equals(expect.get(i).getPublicationDate(), result.get(i).getPublicationDate())) return false;

        }
        return true;
    }
}
