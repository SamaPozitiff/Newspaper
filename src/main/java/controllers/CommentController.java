package controllers;

import entities.CommentEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.CommentService;

import java.util.List;

@RestController
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/articles/comments")
    public List<CommentEntity> getCommentsOfArticle(@RequestParam Integer page, Long article){
        return commentService.findCommentsOfArticle(page, article);
    }
}
