package mx.edu.utez.market.models.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "UPDATE users SET status=:status WHERE username=:username",nativeQuery = true)
    Boolean changeStatus(@Param("status")Boolean status,@Param("username")String username);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String Username);
    boolean existsByPersonCurp(String Curp);
}
