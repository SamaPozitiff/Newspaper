package controllers;

import entities.ArticleEntity;
import entities.CommentEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ArticleService;
import services.CommentService;
import services.UserService;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;
    private final ArticleService articleService;
    private final UserService userService;


    public CommentController(CommentService commentService, ArticleService articleService, UserService userService){
        this.commentService = commentService;
        this.userService = userService;
        this.articleService = articleService;
    }

    @GetMapping("/{article}/comments")
    public List<CommentEntity> getCommentsOfArticle(@RequestParam Integer page, @PathVariable Long article){
        return commentService.findCommentsOfArticle(page, article);
    }

    @PostMapping("/{article}/comments/add")
    public ResponseEntity<CommentEntity> addComment(@RequestParam String description, Long userId, @PathVariable Long article){
        return new ResponseEntity<>(commentService.save(new CommentEntity(description, userService.findById(userId), articleService.findById(article))), HttpStatus.CREATED);
    }

    @DeleteMapping (path = "/comments/delete")
    public void deleteComment(@RequestParam Long commentId){
        commentService.delete(commentId);
        }

}
