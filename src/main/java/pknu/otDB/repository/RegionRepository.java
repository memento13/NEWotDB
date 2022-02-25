package pknu.otDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pknu.otDB.entity.Region;

public interface RegionRepository extends JpaRepository<Region,Long> {
}
