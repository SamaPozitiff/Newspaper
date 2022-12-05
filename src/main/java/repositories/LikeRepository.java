package repositories;

import entities.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/*
репозиторий для лайков
 */
@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    /**
    получение количества лайков у конкретной статьи
     @param article - id статьи
     */
    @Query (value = "select count(*) from likes l where l.article_id= :article ;",
            nativeQuery = true)
    Long getAmountLikesFromArticle(@Param("article") Long article);
    /**
    проверка лайкнул ли пользователь конкретную статью
     @param userId - id пользователя
     @param articleId - id статьи
     */
    @Query (nativeQuery = true, value = "select count(*) from likes l where l.user_id= :user and l.article_id = :article ;")
    Long isUserLikeThisArticle(@Param("user") Long userId,
                               @Param("article") Long articleId);
    /**
    получение Id лайка
     @param userId - id пользователя
     @param articleId - id статьи
     */
    @Query(nativeQuery = true, value = "select id from likes where user_id = :user and article_id = :article ;")
    Long getLikeId(@Param("user") Long userId,
                   @Param("article") Long articleId);



}
