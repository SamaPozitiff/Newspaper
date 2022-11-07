package Services;

import Entities.NewspaperArticleEntity;
import Repositories.NewspaperArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final NewspaperArticleRepository repository;


    public ArticleService(NewspaperArticleRepository repository){
        this.repository = repository;
    }

    public List<NewspaperArticleEntity> getAllArticlesFor24Hours(){
        return repository.findAllArticlesForLast24Hours();
    }

    public List<NewspaperArticleEntity> get3LastArticles(){
        return repository.findLast3Articles();
    }

    public void save(NewspaperArticleEntity entity){
        repository.save(entity);
    }
}
