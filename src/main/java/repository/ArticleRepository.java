package repository;

import entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
репозиторий статьи
 */
@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    /**
    получение всех статей за последние 24 часа
     */
    @Query(nativeQuery = true, value = "SELECT * FROM articles a WHERE a.date BETWEEN DATE_TRUNC('DAY', current_timestamp - INTERVAL '1'DAY) AND CURRENT_TIMESTAMP ORDER BY date DESC;")
    List<ArticleEntity> findAllArticlesForLast24Hours();
}
