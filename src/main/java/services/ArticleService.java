package services;

import entities.ArticleEntity;
import entities.CommentEntity;
import repositories.ArticleRepository;
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

    public ArticleEntity findById(Long id){
        return repository.findById(id).get();
    }
}
