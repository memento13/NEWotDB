package pknu.otDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pknu.otDB.entity.Warehouse;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {

    List<Warehouse> findAllByOrderById();

}
