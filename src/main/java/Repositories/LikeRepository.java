package Repositories;

import Entities.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    @Query (value = "select count(*) from likes l where l.article_id= :article ;",
            nativeQuery = true)
    Long getAmountLikesFromArticle(@Param("article") Long article);

    @Query (nativeQuery = true, value = "select count(*) from likes l where l.user_id= :user and l.article_id = :article ;")
    Long isUserLikeThisArticle(@Param("user") Long user,
                               @Param("article") Long article);

}
