package controllers;

import security.SecurityConfig;
import entities.ArticleEntity;
import entities.CommentEntity;
import org.springframework.stereotype.Component;
import services.ArticleService;
import services.CommentService;
import services.LikeService;
import services.UserService;

import java.util.ArrayList;
import java.util.List;
@Component
public class NewspaperFacade {
    ArticleService articleService;
    CommentService commentService;
    LikeService likeService;
    UserService userService;
    SecurityConfig securityConfig;
    public NewspaperFacade(ArticleService articleService, CommentService commentService,
                           LikeService likeService, UserService userService,
                           SecurityConfig securityConfig){
        this.articleService = articleService;
        this.commentService = commentService;
        this.likeService = likeService;
        this.userService = userService;
        this.securityConfig = securityConfig;

    }

    /*
    Главная страница
     */
    public List homepage(){
        List homepage = new ArrayList<>();
        if(securityConfig.isAuthenticated()){
            homepage.add(userService.getNameAuthorizedUser());
        }
        List<ArticleEntity> articles = articleService.getAllArticlesFor24Hours();
        for (ArticleEntity article :articles){
            homepage.add(article);
            homepage.add(likeService.getAmountLikesFromArticle(article.getId()));
            if (securityConfig.isAuthenticated()){
                homepage.add(likeService.isUserLikeThisArticle(article.getId()));
            }else {
                homepage.add(false);
            }
            homepage.add(commentService.findCommentsOfArticle(0,article.getId()));
        }
        return homepage;
    }

    /*
    получение комментариев
     */
    public List getCommentsOnArticle(int page, Long articleId){
        return commentService.findCommentsOfArticle(page, articleId);
    }
    /*
    добавление комментария
     */
    public void addComment(String description, Long articleId){
        commentService.save(new CommentEntity(description, userService.findById(userService.getIdAuthorizedUser()),articleService.findById(articleId)));
    }
    /*
    снятие/простановка лайка
     */
    public void likeArticle(Long articleId){
        likeService.likeArticle(articleId);
    }
}
