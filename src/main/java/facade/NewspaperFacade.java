package facade;

import entity.CommentEntity;
import entity.UserEntity;
import mapper.*;
import rest_dto.ArticleDTO;
import rest_dto.CommentDTO;
import jwt_auth.SecurityConfig;
import entity.ArticleEntity;
import org.springframework.stereotype.Component;
import service.ArticleService;
import service.CommentService;
import service.LikeService;
import service.UserService;

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


    public NewspaperFacade(ArticleService articleService, CommentService commentService,
                           LikeService likeService, UserService userService,
                           SecurityConfig securityConfig, ArticleMapper articleMapper, CommentMapper commentMapper){
        this.articleService = articleService;
        this.commentService = commentService;
        this.likeService = likeService;
        this.userService = userService;
        this.securityConfig = securityConfig;
        this.articleMapper = articleMapper;
        this.commentMapper = commentMapper;
    }

    /**
    Главная страница
     */
    public List<ArticleDTO> homepage() throws IOException {
        List<ArticleDTO> homepage = new ArrayList<>();
        List<ArticleEntity> articles = articleService.getAllArticlesFor24Hours();
        for (ArticleEntity article:articles){
            List<CommentDTO> comments = getCommentsOnArticle(0, article.getId());
            homepage.add(articleMapper.toArticleDto(article, comments, likeService.getAmountLikesFromArticle(article.getId()), securityConfig.isAuthenticated() && likeService.isUserLikeThisArticle(article, userService.getByEmail(securityConfig.getCurrentUsername()))));
        }
        return homepage;
    }

    /**
    получение комментариев
     */
    public List<CommentDTO> getCommentsOnArticle(int page, Long articleId){
        List<CommentDTO> comments = new ArrayList<>();
        List<CommentEntity> commentEntities = commentService.findPagedCommentsOfArticle(page, articleId);
        for (CommentEntity comment:commentEntities){
            comments.add(commentMapper.toCommentDTO(comment));
        }
        return comments;
    }
    //TODO проверить добавление комментария и простановку лайка
    /**
    добавление комментария
     */
    public void addComment(String description, Long articleId){
        commentService.save(commentService.newComment(description, userService.getByEmail(securityConfig.getCurrentUsername()), articleService.findById(articleId)));
    }
    /**
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
}
