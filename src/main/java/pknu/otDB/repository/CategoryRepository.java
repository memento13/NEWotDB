package pknu.otDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pknu.otDB.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("select c from Category c where c.id = :id")
    Category findOneById(@Param("id") Long id);

    List<Category> findAllByName(String name);

    Category findTopByOrderByIdDesc();
}
