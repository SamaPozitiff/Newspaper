package Services;

import Entities.ArticleEntity;
import Entities.CommentEntity;
import Repositories.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository repository;


    public ArticleService(ArticleRepository repository){
        this.repository = repository;
    }

    public List<ArticleEntity> getAllArticlesFor24Hours(){
        return repository.findAllArticlesForLast24Hours();
    }

    public List<ArticleEntity> get3LastArticles(){
        return repository.findLast3Articles();
    }

    public void save(ArticleEntity entity){
        repository.save(entity);
    }

    public void addComment(ArticleEntity article, CommentEntity comment){
        article.getComments().add(comment);
    }

    public ArticleEntity findById(Long id){
        return repository.findById(id).get();
    }
}
