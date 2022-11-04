package Services;

import Repositories.NewspaperArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final NewspaperArticleRepository repository;


    public ArticleService(NewspaperArticleRepository repository){
        this.repository = repository;
    }

    public List findAllArticlesFor24Hours(){
        return repository.findAllArticlesForLast24Hours();
    }
}
