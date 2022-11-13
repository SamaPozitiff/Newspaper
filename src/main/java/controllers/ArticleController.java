package controllers;


import entities.ArticleEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ArticleService;
import org.springframework.http.MediaType;
import services.CommentService;
import services.UserService;


import java.util.List;

@RestController
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

    public ArticleController(ArticleService articleService, UserService userService){
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping(path = "/articles" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleEntity> getAllArticlesFor24Hours(){
        return articleService.getAllArticlesFor24Hours();
    }

    @GetMapping(path = "/last_articles",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleEntity> get3LastArticles(){
        return articleService.get3LastArticles();
    }

    @PostMapping(path = "/articles/add")
    public ResponseEntity<ArticleEntity> addArticle(@RequestParam String title, String description, Long userId){
       return new ResponseEntity<>(articleService.save(new ArticleEntity(title, null, description, userService.findById(userId))), HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/articles/delete")
    public void delete(@RequestParam Long articleId){
        articleService.delete(articleId);
    }

}
