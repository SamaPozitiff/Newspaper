package Repositories;

import Entities.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    @Query (nativeQuery = true, value = "select count(*) from likes l where l.article_id= id;")
    long getAmountLikesFromArticle(@Param("id") long id);

    @Query(nativeQuery = true, value = "select count(*) from likes where article_id = aaarticle and user_id = uuuser;")
    long isUserLikeThisArticle(@Param("uuuser") long userId, @Param("aaarticle")long articleId);

}
