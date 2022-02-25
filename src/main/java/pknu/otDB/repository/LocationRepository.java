package pknu.otDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pknu.otDB.entity.Country;
import pknu.otDB.entity.Location;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location,Long> {

    List<Location> findAllByCountry(Country country);
}
