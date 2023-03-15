package mx.edu.utez.market.models.subcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    @Query(value = "UPDATE subcategory SET status=:status where name=:name", nativeQuery = true)
    boolean findByDesc(@Param("status") Boolean status, @Param("name") String name);
    @Query(value = "UPDATE subcatery SET status=:status where id=:id",nativeQuery = true)
    boolean findById(@Param("status")Boolean status, @Param("id")Long id);
    boolean existsByName(String name);
}
