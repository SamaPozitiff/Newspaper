package service;

import entity.ArticleEntity;
import entity.LikeEntity;
import entity.UserEntity;
import newspaper_main.HomePageNewsPaperApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import repository.LikeRepository;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
@ContextConfiguration(initializers = {PSQLContainer.Initializer.class})
@Testcontainers
public class LikeServiceTest extends PSQLContainer{

    @Autowired
    LikeService likeService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    LikeRepository likeRepository;

    @Transactional
    @Test
    public void like(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        LikeEntity like = likeService.newLike(article, user);
        likeService.like(article, user);
        Assertions.assertTrue(like.getArticle().equals(likeService.getLike(article, user).getArticle()));
        Assertions.assertTrue(like.getUser().equals(likeService.getLike(article, user).getUser()));

    }

    @Transactional
    @Test
    public void dislike(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        LikeEntity like = likeService.newLike(article, user);
        likeService.like(article, user);
        Assertions.assertTrue(like.getArticle().equals(likeService.getLike(article, user).getArticle()));
        Assertions.assertTrue(like.getUser().equals(likeService.getLike(article, user).getUser()));
        likeService.dislike(article, user);
        Assertions.assertTrue(likeService.getLike(article, user) == null);
    }

    @Transactional
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
        Assertions.assertTrue(expect.getArticle().equals(result.getArticle()));
        Assertions.assertTrue(expect.getUser().equals(result.getUser()));

    }

    @Transactional
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

    @Transactional
    @Test
    public void isUserLikeThisArticle(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        likeService.like(article, user);
        Assertions.assertTrue(likeService.isUserLikeThisArticle(article, user));
    }

    @Transactional
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
        Assertions.assertTrue(expect.getArticle().equals(likeService.getLike(article,user).getArticle()));
        Assertions.assertTrue(expect.getUser().equals(likeService.getLike(article,user).getUser()));

    }
}
