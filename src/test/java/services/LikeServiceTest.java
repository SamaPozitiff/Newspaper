package services;

import entities.ArticleEntity;
import entities.LikeEntity;
import entities.UserEntity;
import com.example.homepagenewspaper.HomePageNewsPaperApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LikeServiceTest {
    @Autowired
    LikeService likeService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    UserEntity user1;
    UserEntity user2;
    ArticleEntity article1;
    ArticleEntity article2;

    @BeforeAll
    public void usersAndArticles(){
        user1  = new UserEntity("qwerty", "", "??????", "ivan@gmail.com");
        user2 = new UserEntity("12345", "??????", "???????", "serg@gmail.com");
        article1 = new ArticleEntity("hello", null, "world", user1);
        article2 = new ArticleEntity("test", null, "test", user2);
    }
    @Test
    public void likeTestIfEmpty(){
        Assertions.assertEquals(0,likeService.getAmountLikesFromArticle(999));
    }

    @Test
    public void likeTest(){
        userService.save(user1);
        userService.save(user2);
        articleService.save(article1);
        articleService.save(article2);
        LikeEntity likeEntity1 = new LikeEntity(article1,user1);
        LikeEntity likeEntity2 = new LikeEntity(article1, user2);
        LikeEntity likeEntity3 = new LikeEntity(article2, user1);
        likeService.save(likeEntity1);
        likeService.save(likeEntity2);
        likeService.save(likeEntity3);
        Assertions.assertEquals(2, likeService.getAmountLikesFromArticle(article1.getId()));
    }



    @Test
    public void isUserLikeThisIfDBEmpty(){
        Assertions.assertEquals(false, likeService.isUserLikeThisArticle(33, 55));
         }

    @Test
    public void isUserLikeThis(){
        userService.save(user1);
        articleService.save(article1);
        LikeEntity likeEntity = new LikeEntity(article1, user1);
        likeService.save(likeEntity);
        Assertions.assertEquals(true, likeService.isUserLikeThisArticle(user1.getId(), article1.getId()));

    }


}
