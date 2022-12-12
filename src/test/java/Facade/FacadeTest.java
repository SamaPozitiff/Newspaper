package Facade;

import com.example.homepagenewspaper.AssertsTools;
import com.example.homepagenewspaper.HomePageNewsPaperApplication;
import controllers.NewspaperFacade;
import entities.ArticleEntity;
import entities.CommentEntity;
import entities.LikeEntity;
import entities.UserEntity;
import mappers.ArticleMapper;
import mappers.CommentMapperImpl;
import mappers.LikeMapperImpl;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import restDTO.ArticleDTO;
import restDTO.CommentDTO;
import services.ArticleService;
import services.CommentService;
import services.LikeService;
import services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
public class FacadeTest {
    @Autowired
    NewspaperFacade newspaperFacade;
    @Autowired
    CommentMapperImpl commentMapper;
    @Autowired
    AssertsTools assertsTools;
    @Autowired
    LikeService likeService;
    @Autowired
    LikeMapperImpl likeMapper;



    @Test
    public void homepage(ApplicationContext ctx) throws IOException {
        UserService userService = (UserService) ctx.getBean("userService");
        ArticleService articleService = (ArticleService) ctx.getBean("articleService");
        LikeService likeService = (LikeService) ctx.getBean("likeService");
        CommentService commentService = (CommentService) ctx.getBean("commentService");
        ArticleMapper articleMapper = (ArticleMapper) ctx.getBean("articleMapperImpl");
        List<ArticleDTO> expect = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            UserEntity user = userService.newUser("" + i + "@email", "password", "name", "lastname", "ROLE_USER");
            userService.save(user);
            ArticleEntity article = articleService.newArticle("title" + i, "src/main/resources/image1.png", "description", user);
            articleService.save(article);
            likeService.like(article, user);
            CommentEntity comment = commentService.newComment("description", user, article);
            commentService.save(comment);
            likeService.like(article,user);
            ArticleDTO articleDTO = articleMapper.toArticleDto(article, newspaperFacade.getCommentsOnArticle(0, article.getId()), likeService.getAmountLikesFromArticle(article.getId()), false);
            expect.add(0, articleDTO);
        }
        Assertions.assertTrue(assertsTools.compareListArticleDTO(expect, newspaperFacade.homepage()));

    }


    @Test
    public void getComments(ApplicationContext ctx){
        UserEntity user = addUser(ctx);
        ArticleEntity article = addArticle(ctx, user);
        CommentEntity comment = addCommentEntity(ctx, user, article);
        List<CommentDTO> comments = Arrays.asList(commentMapper.toCommentDTO(comment));
        Assertions.assertTrue(assertsTools.compareListCommentDTO(comments, newspaperFacade.getCommentsOnArticle(0, article.getId())));
    }

    @Test
    public void addComment(ApplicationContext ctx){
        UserEntity user = addUser(ctx);
        ArticleEntity article = addArticle(ctx, user);
        CommentService commentService = (CommentService) ctx.getBean("commentService");
        CommentEntity comment = commentService.newComment("description", user, article);
        newspaperFacade.addComment(comment.getDescription(), article.getId());
        List<CommentDTO> expect = Arrays.asList(commentMapper.toCommentDTO(comment));
        Assertions.assertTrue(assertsTools.compareListCommentDTO(expect,         newspaperFacade.getCommentsOnArticle(0, article.getId())));
    }

    public UserEntity addUser(ApplicationContext ctx){
        UserService userService = (UserService) ctx.getBean("userService");
        UserEntity user = userService.newUser( "test@email", "password", "name", "lastname", "ROLE_USER");
        userService.save(user);
        return user;
    }

    public ArticleEntity addArticle(@NotNull ApplicationContext ctx, UserEntity user){
        ArticleService articleService = (ArticleService) ctx.getBean("articleService");
        ArticleEntity article = articleService.newArticle("title", "image", "description", user);
        articleService.save(article);
        return article;
    }

    public CommentEntity addCommentEntity(ApplicationContext ctx, UserEntity user, ArticleEntity article){
        CommentService commentService = (CommentService) ctx.getBean("commentService");
        CommentEntity comment = commentService.newComment("comment", user, article);
        commentService.save(comment);
        return comment;
    }
}
