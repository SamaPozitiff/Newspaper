package repositories;

import entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query (nativeQuery = true,
            value = "SELECT * FROM newspaper_users where email = usersEmail")
    UserEntity findByEmail(@Param("email") String usersEmail);
}
