package services;


import com.example.homepagenewspaper.AssertsTools;
import com.example.homepagenewspaper.HomePageNewsPaperApplication;
import entities.ArticleEntity;
import entities.CommentEntity;
import entities.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import repositories.CommentRepository;


import java.util.*;

    @SpringBootTest(classes = HomePageNewsPaperApplication.class)
    public class CommentServiceTest {

        @Autowired    CommentService commentService;

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    AssertsTools assertsTools;

    @Test
    public void save(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        CommentEntity comment = commentService.newComment("description", user, article);
        commentService.save(comment);
        Assertions.assertTrue(assertsTools.compareComment(comment, commentService.findById(comment.getId())));

    }

    @Test
    public void findPagedCommentsOfArticle(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        List<CommentEntity> page0 = new ArrayList<>();
        List<CommentEntity> page1 = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            CommentEntity comment = commentService.newComment("description" + i, user, article);
            commentService.save(comment);
            if(i<2){
                page1.add(0, comment);
            }else {
                page0.add(0, comment);
            }
        }
        Assertions.assertTrue(assertsTools.compareListOfComments(page0, commentService.findPagedCommentsOfArticle(0, article.getId())));
        Assertions.assertTrue(assertsTools.compareListOfComments(page1, commentService.findPagedCommentsOfArticle(1, article.getId())));
    }

    @Test
    public void delete(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("tit;e", "image", "description", user);
        articleService.save(article);
        CommentEntity comment = commentService.newComment("description", user, article);
        commentService.save(comment);
        Assertions.assertTrue(assertsTools.compareComment(comment, commentService.findById(comment.getId())));
        commentService.delete(comment.getId());
        Assertions.assertFalse(assertsTools.compareComment(comment, commentService.findById(comment.getId())));
    }

    @Test
    public void findAllComments(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        userService.save(user);
        ArticleEntity article = articleService.newArticle("tit;e", "image", "description", user);
        articleService.save(article);
        List<CommentEntity> expect = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            CommentEntity comment = commentService.newComment("description" + i, user, article);
            expect.add(0,comment);
            commentService.save(comment);
        }
        Assertions.assertTrue(assertsTools.compareListOfComments(expect, commentService.findAllCommentsOfArticle(article.getId())));
    }

    @Test
    public void newComment(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        ArticleEntity article = articleService.newArticle("tit;e", "image", "description", user);
        CommentEntity expect = new CommentEntity();
        expect.setArticle(article);
        expect.setDescription("description");
        expect.setUser(user);
        CommentEntity result = commentService.newComment("description", user, article);
        Assertions.assertEquals(expect.getDescription(), result.getDescription());
        Assertions.assertTrue(assertsTools.compareArticle(expect.getArticle(), result.getArticle()));
        Assertions.assertTrue(assertsTools.compareUser(expect.getUser(), result.getUser()));    }

    @Test
    public void newCommentWithDate(){
        UserEntity user = userService.newUser("email", "password", "firstname", "lastname", "ROLE_USER");
        ArticleEntity article = articleService.newArticle("tit;e", "image", "description", user);
        CommentEntity expect = new CommentEntity();
        Calendar date = new GregorianCalendar();
        date.add(Calendar.DAY_OF_YEAR, -2);
        expect.setUser(user);
        expect.setPublicationDate(date.getTime());
        expect.setDescription("description");
        expect.setArticle(article);
        CommentEntity result = commentService.newCommentWithDate("description", user, article, date.getTime());
        Assertions.assertEquals(expect.getDescription(), result.getDescription());
        Assertions.assertTrue(assertsTools.compareUser(expect.getUser(), result.getUser()));
        Assertions.assertTrue(assertsTools.compareArticle(expect.getArticle(), result.getArticle()));
        Assertions.assertEquals(expect.getPublicationDate(), result.getPublicationDate());
    }
}
