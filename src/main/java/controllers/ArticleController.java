package controllers;


import entities.ArticleEntity;
import entities.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import services.ArticleService;
import org.springframework.http.MediaType;
import services.CommentService;
import services.UserService;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path ="/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

    public ArticleController(ArticleService articleService, UserService userService){
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping(path = "" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleEntity> getAllArticlesFor24Hours(){
        return articleService.getAllArticlesFor24Hours();
    }


    @PostMapping(path = "/add")
    public ResponseEntity<ArticleEntity> addArticle(@RequestParam String title, String description, @AuthenticationPrincipal UserEntity user){
       return new ResponseEntity<>(articleService.save(new ArticleEntity(title, null, description, user)), HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/delete")
    public void delete(@RequestParam Long articleId){
        articleService.delete(articleId);
    }

}
