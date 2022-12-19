package service;

import entity.ArticleEntity;
import entity.LikeEntity;
import entity.UserEntity;

public interface LikeService {

    void like(ArticleEntity article, UserEntity user);

    void dislike(ArticleEntity article, UserEntity user);

    LikeEntity newLike(ArticleEntity article, UserEntity user);

    long getAmountLikesFromArticle(long id);

    boolean isUserLikeThisArticle(ArticleEntity article, UserEntity user);

    LikeEntity getLike(ArticleEntity article, UserEntity user);
}
