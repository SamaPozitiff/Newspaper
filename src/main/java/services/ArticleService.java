package services;

import entities.ArticleEntity;
import entities.CommentEntity;
import entities.UserEntity;
import repositories.ArticleRepository;
import org.springframework.stereotype.Service;

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
        List<CommentEntity> comments= commentService.findAllCommentsOfArticle(id);
        for (CommentEntity comment:comments){
            commentService.delete(comment.getId());
        }
        repository.deleteById(id);
    }

    public ArticleEntity newArticle(String title, String image, String description, UserEntity user) {
        return new ArticleEntity(title, image, description, user);
    }



}
