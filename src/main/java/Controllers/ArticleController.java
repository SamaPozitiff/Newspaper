package Controllers;


import Entities.NewspaperArticleEntity;
import Services.ArticleService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class ArticleController {
    private final ArticleService service;

    public ArticleController(ArticleService service){
        this.service = service;
    }

    @GetMapping(path = "/articles" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NewspaperArticleEntity> getAllArticlesFor24Hours(){
        return service.getAllArticlesFor24Hours();
    }

    @GetMapping(path = "/last_articles",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NewspaperArticleEntity> get3LastArticles(){
        return service.get3LastArticles();
    }

}
