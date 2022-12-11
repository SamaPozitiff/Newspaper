package controllers;

import entities.CommentEntity;
import entities.UserEntity;
import mappers.*;
import restDTO.ArticleDTO;
import restDTO.CommentDTO;
import restDTO.LikeDTO;
import security.SecurityConfig;
import entities.ArticleEntity;
import org.springframework.stereotype.Component;
import services.ArticleService;
import services.CommentService;
import services.LikeService;
import services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class NewspaperFacade {
    private final ArticleService articleService;
    private final CommentService commentService;
    private final LikeService likeService;
    private final UserService userService;
    private final SecurityConfig securityConfig;
    private final ArticleMapper articleMapper;
    private final CommentMapper commentMapper;
    private final LikeMapper likeMapper;
    private final UserMapper userMapper;
    public NewspaperFacade(ArticleService articleService, CommentService commentService,
                           LikeService likeService, UserService userService,
                           SecurityConfig securityConfig, ArticleMapperImpl articleMapper, CommentMapperImpl commentMapper, LikeMapperImpl likeMapper, UserMapperImpl userMapper){
        this.articleService = articleService;
        this.commentService = commentService;
        this.likeService = likeService;
        this.userService = userService;
        this.securityConfig = securityConfig;
        this.articleMapper = articleMapper;

        this.commentMapper = commentMapper;
        this.likeMapper = likeMapper;
        this.userMapper = userMapper;
    }

    /*
    Главная страница
     */
    public List<ArticleDTO> homepage() throws IOException {
        List<ArticleDTO> homepage = new ArrayList<>();
        LikeDTO like = null;
        List<ArticleEntity> articles = articleService.getAllArticlesFor24Hours();
        for (ArticleEntity article:articles){

            List<CommentDTO> comments = getCommentsOnArticle(0, article.getId());

           if (securityConfig.isAuthenticated() && likeService.isUserLikeThisArticle(article, userService.getByEmail(securityConfig.getCurrentUsername()))){
               like = likeMapper.toLikeDTO(likeService.getLike(article,userService.getByEmail(securityConfig.getCurrentUsername())));
           }
            homepage.add(articleMapper.toArticleDto(article, comments, likeService.getAmountLikesFromArticle(article.getId()), like));
        }
        return homepage;
    }

    /*
    получение комментариев
     */
    public List<CommentDTO> getCommentsOnArticle(int page, Long articleId){
        List<CommentDTO> comments = new ArrayList<>();
        List<CommentEntity> commentEntities = commentService.findCommentsOfArticle(page, articleId);
        for (CommentEntity comment:commentEntities){
            comments.add(commentMapper.toCommentDTO(comment));
        }
        return comments;
    }
    //TODO проверить добавление комментария и простановку лайка
    /*
    добавление комментария
     */
    public void addComment(String description, Long articleId){
        commentService.save(commentService.newComment(description, userService.getByEmail(securityConfig.getCurrentUsername()), articleService.findById(articleId)));
    }
    /*
    снятие/простановка лайка
     */
    public void likeArticle(Long articleId){
       ArticleEntity article = articleService.findById(articleId);
        UserEntity user = userService.getByEmail(securityConfig.getCurrentUsername());
        if(likeService.isUserLikeThisArticle(article, user)){
           likeService.dislike(article, user);
       }else {
            likeService.like(article,user);
        }
    }

    public void deleteArticle(Long articleId){
        if(!commentService.findAllCommentsOfArticle(articleId).isEmpty()){
            List<CommentEntity> comments= commentService.findAllCommentsOfArticle(articleId);
            for (CommentEntity comment:comments){
                commentService.delete(comment.getId());
            }
        }
        articleService.delete(articleId);
    }
}
