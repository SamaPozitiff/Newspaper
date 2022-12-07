package services;

import entities.ArticleEntity;
import entities.LikeEntity;
import entities.UserEntity;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
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

    public void like(ArticleEntity article, UserEntity user){
        save(newLike(article,user));
    }

    public void dislike(ArticleEntity article, UserEntity user){
        delete(article, user);
    }

    public LikeEntity newLike(ArticleEntity article, UserEntity user){
        return new LikeEntity(article, user);
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
    public boolean isUserLikeThisArticle(ArticleEntity article, UserEntity user){
        return repository.isUserLikeThisArticle(user.getId(), article.getId()) > 0;
    }

    /*
    сохранить лайк
     */
    @NotNull
    private LikeEntity save (@NonNull LikeEntity like){
        return repository.save(like);
    }

    private void delete(@NonNull ArticleEntity article, @NonNull UserEntity user){
        repository.delete(getLike(article, user));
    }

    public LikeEntity getLike(ArticleEntity article, UserEntity user){
        return repository.getLike(article.getId(), user.getId());
    }

}
