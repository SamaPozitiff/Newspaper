package repository;

import entity.CommentEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
/*
репозиторий для комментариев
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    /**
    получение всех комментариев к конкретной статье
     @param article - id статьи
     */
    @Query (nativeQuery = true, value = "select * from comments where article_id = :article ORDER BY date DESC;")
    List<CommentEntity> getCommentsOfArticle(@Param("article") Long article);







}
