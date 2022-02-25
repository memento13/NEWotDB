package pknu.otDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pknu.otDB.entity.Country;
import pknu.otDB.entity.Region;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Long> {

    List<Country> findAllByRegion(Region region);

    Country findOneById(String id);
}
