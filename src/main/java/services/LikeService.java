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
    /*
    лайкнуть статью(если лайк был - убрать, если не было - добавить)
     */
    public void likeArticle(Long articleId) {
       if(isUserLikeThisArticle(articleId)){
           delete(getLikeId(userService.getIdAuthorizedUser(), articleId));
       }else {
           save(new LikeEntity(articleService.findById(articleId), userService.findById(userService.getIdAuthorizedUser())));
       }
    }
    /*
    получение количества лайков на статье
     */
    public long getAmountLikesFromArticle(long id){
        return repository.getAmountLikesFromArticle(id);
    }
    /*
    проверка лайкнул ли текущий авторизованный пользователь статью
     */
    public boolean isUserLikeThisArticle(long articleId){
        return repository.isUserLikeThisArticle(userService.getIdAuthorizedUser(), articleId) > 0 ? true : false;
    }
    /*
    найти лайк по id
     */
    public LikeEntity getLike(Long id){
        return repository.findById(id).get();
    }
    /*
    сохранить комментарий
     */
    public LikeEntity save (LikeEntity like){
        return repository.save(like);
    }
    /*
    удаление комментария
     */
    public void delete(Long id){
        repository.deleteById(id);
    }
    /*
    получение id лайка
     */
    public Long getLikeId(Long  userId, Long articleId){
        return repository.getLikeId(userId, articleId);
    }
}
