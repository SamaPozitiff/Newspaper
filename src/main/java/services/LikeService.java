package services;

import entities.LikeEntity;
import repositories.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    LikeRepository repository;
    UserService userService;
    ArticleService articleService;
    public LikeService(LikeRepository repository, UserService userService,
                       ArticleService articleService) {
        this.repository = repository;
        this.userService = userService;
        this.articleService = articleService;
    }

    public void likeArticle(Long articleId) {
       if(isUserLikeThisArticle(articleId)){
           delete(getLikeId(userService.getIdAuthorizedUser(), articleId));
       }else {
           save(new LikeEntity(articleService.findById(articleId), userService.findById(userService.getIdAuthorizedUser())));
       }
    }

    public long getAmountLikesFromArticle(long id){
        return repository.getAmountLikesFromArticle(id);
    }

    public boolean isUserLikeThisArticle(long articleId){
        return repository.isUserLikeThisArticle(userService.getIdAuthorizedUser(), articleId) > 0 ? true : false;
    }

    public LikeEntity getLike(Long id){
        return repository.findById(id).get();
    }

    public LikeEntity save (LikeEntity like){
        return repository.save(like);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Long getLikeId(Long  userId, Long articleId){
        return repository.getLikeId(userId, articleId);
    }
}
