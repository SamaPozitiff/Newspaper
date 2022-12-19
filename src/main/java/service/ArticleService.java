package service;

import entity.ArticleEntity;
import entity.UserEntity;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

public interface ArticleService {

    List<ArticleEntity> getAllArticlesFor24Hours();

    ArticleEntity save(ArticleEntity entity);

    ArticleEntity findById(Long id);

    ArticleEntity newArticle(@NonNull String title, String image, @NonNull String description,
                             @NonNull UserEntity user);

    ArticleEntity newArticleWithDate(@NonNull String title, String image, @NonNull String description,
                                     @NonNull UserEntity user, @NonNull Date date);
}
