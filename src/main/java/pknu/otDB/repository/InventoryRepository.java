package pknu.otDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pknu.otDB.entity.Inventory;
import pknu.otDB.entity.InventoryId;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {

    @Query("select i from Inventory i where i.id.warehouse in (select w from Warehouse w where w.location.id = :locationId) and i.id.product.id = :productId")
    List<Inventory> searchAllByLocationAndProduct(@Param("locationId") Long locationId,@Param("productId") Long productId);

}
