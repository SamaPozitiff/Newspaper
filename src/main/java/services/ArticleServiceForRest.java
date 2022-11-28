package services;

import entities.ArticleEntity;

public interface ArticleServiceForRest {
    Iterable<ArticleEntity> findArticlesFor24Hours();
}
