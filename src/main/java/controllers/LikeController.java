package controllers;

import entities.LikeEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ArticleService;
import services.LikeService;
import services.UserService;

@RestController
public class LikeController {
    LikeService likeService;
    ArticleService articleService;
    UserService userService;

    public LikeController(LikeService likeService, ArticleService articleService, UserService userService){
        this.likeService = likeService;
        this.articleService = articleService;
        this.userService = userService;
    }


    @PostMapping(path = "/article/like/add")
    public ResponseEntity<LikeEntity> addLike(@RequestParam Long articleId, Long userId){
            return new ResponseEntity<>(likeService.save(new LikeEntity(articleService.findById(articleId), userService.findById(userId))), HttpStatus.CREATED);
    }

    @DeleteMapping (path = "/article/like/delete")
    public void deleteLike(@RequestParam Long userId, Long articleId){
        if (likeService.isUserLikeThisArticle(userId, articleId)){
            likeService.delete(likeService.getLikeId(userId,articleId));
        }
    }


}
