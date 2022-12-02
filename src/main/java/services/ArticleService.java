package services;

import entities.ArticleEntity;
import entities.CommentEntity;
import repositories.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository repository;
    private final CommentService commentService;


    public ArticleService(ArticleRepository repository, CommentService commentService){
        this.repository = repository;
        this.commentService = commentService;
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


}
