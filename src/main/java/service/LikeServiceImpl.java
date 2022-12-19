package service;

import entity.ArticleEntity;
import entity.LikeEntity;
import entity.UserEntity;
import lombok.NonNull;
import repository.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService{
    LikeRepository repository;
    UserService userService;
    ArticleService articleService;

    public LikeServiceImpl(LikeRepository repository, UserService userService,
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
        LikeEntity like = new LikeEntity();
        like.setArticle(article);
        like.setUser(user);
        return like;
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


    public LikeEntity getLike(ArticleEntity article, UserEntity user){
        try {
            return repository.getLike(article.getId(), user.getId());
        }catch (Exception ex){
            return null;
        }
    }
    /*
        сохранить лайк
         */
    private void save (@NonNull LikeEntity like){
        repository.save(like);
    }

    private void delete(@NonNull ArticleEntity article, @NonNull UserEntity user){
        repository.delete(getLike(article, user));
    }

}