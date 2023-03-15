package mx.edu.utez.market.models.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {
        @Query(
                value = "UPDATE categorie SET status = :status WHERE id = :id", nativeQuery = true
        )
                boolean updateStatusVyId(
                        @Param("status") Boolean status,
                        @Param("id") Long id
        );
        boolean existsByName(String name);
}
