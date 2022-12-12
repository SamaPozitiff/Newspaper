package services;

import com.example.homepagenewspaper.AssertsTools;
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
import repositories.LikeRepository;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LikeServiceTest {

    @Autowired
    LikeService likeService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    AssertsTools assertsTools;
    @Autowired
    LikeRepository likeRepository;

    @Test
    public void like(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        LikeEntity like = likeService.newLike(article, user);
        likeService.like(article, user);
        Assertions.assertTrue(assertsTools.compareArticle(like.getArticle(), likeService.getLike(article, user).getArticle()));
        Assertions.assertTrue(assertsTools.compareUser(like.getUser(), likeService.getLike(article, user).getUser()));

    }

    @Test
    public void dislike(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        LikeEntity like = likeService.newLike(article, user);
        likeService.like(article, user);
        Assertions.assertTrue(assertsTools.compareArticle(like.getArticle(), likeService.getLike(article, user).getArticle()));
        Assertions.assertTrue(assertsTools.compareUser(like.getUser(), likeService.getLike(article, user).getUser()));
        likeService.dislike(article, user);
        Assertions.assertTrue(likeService.getLike(article, user) == null);
    }

    @Test
    public void newLike(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        LikeEntity expect = new LikeEntity();
        expect.setArticle(article);
        expect.setUser(user);
        LikeEntity result = likeService.newLike(article, user);
        Assertions.assertTrue(assertsTools.compareArticle(expect.getArticle(), result.getArticle()));
        Assertions.assertTrue(assertsTools.compareUser(expect.getUser(), result.getUser()));

    }
    @Test
    public void getAmountLikesFromArticles(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        for(int i = 0; i < 5; i++){
            UserEntity userForLike = userService.newUser("email" + i + "@mail.ru", "password", "firstname", "lastname", "ROLE_USER");
            userService.save(userForLike);
            likeService.like(article, userForLike);
        }
        Assertions.assertEquals(5L, likeService.getAmountLikesFromArticle(article.getId()));
    }
    @Test
    public void isUserLikeThisArticle(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        likeService.like(article, user);
        Assertions.assertTrue(likeService.isUserLikeThisArticle(article, user));
    }


    @Test
    public void getLike(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        LikeEntity expect = new LikeEntity();
        expect.setUser(user);
        expect.setArticle(article);
        likeService.like(article, user);
        Assertions.assertTrue(assertsTools.compareArticle(expect.getArticle(), likeService.getLike(article,user).getArticle()));
        Assertions.assertTrue(assertsTools.compareUser(expect.getUser(), likeService.getLike(article,user).getUser()));

    }
}
