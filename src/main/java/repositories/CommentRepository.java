package repositories;

import entities.CommentEntity;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query (nativeQuery = true, value = "select * from comments where article_id = :article ORDER BY date DESC LIMIT 5;")
    List<CommentEntity> getCommentsOfArticle(@Param("article") Long article, Pageable pageable);

}
