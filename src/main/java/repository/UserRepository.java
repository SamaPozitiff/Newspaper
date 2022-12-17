package repository;

import entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/*
репозиторий пользователей
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
    поиск пользователя по email
     @param usersEmail - электронная почта пользователя
     */
    @Query (nativeQuery = true,
            value = "SELECT * FROM newspaper_users where email = :email ;")
    UserEntity findByEmail(@Param("email") String usersEmail);
}
