package services;

import entities.ArticleEntity;

import entities.UserEntity;
import lombok.NonNull;
import repositories.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository repository;
    private final CommentService commentService;
    private final UserService userService;


    public ArticleService(ArticleRepository repository, CommentService commentService, UserService userService){
        this.repository = repository;
        this.commentService = commentService;
        this.userService = userService;
    }
    /*
    получение статей за последние 24 часа
     */
    public List<ArticleEntity> getAllArticlesFor24Hours(){
        return repository.findAllArticlesForLast24Hours();
    }
    /*
    сохранение статьи
     */
    public ArticleEntity save(ArticleEntity entity){
        return repository.save(entity);
    }
    /*
    поиск статьи по id
     */
    public ArticleEntity findById(Long id){
        return repository.findById(id).get();
    }
    /*
    удаление статьи и всех комментариев к ней
     */
    public void delete (Long id){
        repository.deleteById(id);
    }

    public ArticleEntity newArticle(@NonNull String title, String image,@NonNull String description,@NonNull UserEntity user) {
        ArticleEntity article = new ArticleEntity();
        article.setTitle(title);
        article.setImage(image);
        article.setDescription(description);
        article.setAuthor(user);
        article.setPublicationDate(new Date());
        return article;
    }

    public ArticleEntity newArticleWithDate(@NonNull String title, String image, @NonNull String description, @NonNull UserEntity user, @NonNull Date date){
        ArticleEntity article = newArticle(title, image, description, user);
        article.setPublicationDate(date);
        return  article;
    }





}
