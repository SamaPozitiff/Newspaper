package JPARepositories;


import Entities.NewspaperArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewspaperArticleRepository extends JpaRepository<NewspaperArticleEntity, Long> {

}
