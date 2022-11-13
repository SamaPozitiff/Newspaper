package JPARepositories;

import Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJPARepository extends JpaRepository<UserEntity, Long> {


    @Query(nativeQuery = true, value = "SELECT * FROM newspaper_user WHERE email = :email ;")
    public UserEntity getUserByEmail(@Param("email") String email);

}
