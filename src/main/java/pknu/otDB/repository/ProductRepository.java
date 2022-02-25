package pknu.otDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pknu.otDB.entity.Category;
import pknu.otDB.entity.Product;

import javax.persistence.Tuple;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.category = :category order by p.id")
    List<Product> findAllByCategoryOrderById(@Param("category") Category category);

    @Query("SELECT p FROM Product p WHERE lower(p.name) LIKE %:name% order by p.id")
    List<Product> findAllByNameOrderById(@Param("name") String name);

    List<Product> findAllByOrderById();

    @Query(value = "select x.product_id, x.product_name,x.standard_cost,x.list_price,x.category_name,j.quantity from " +
            " (select p.*,c.category_name  from products p join product_categories c on p.category_id = c.category_id) x left join" +
            " (select product_id,sum(quantity) as quantity" +
            " from inventories i left join WAREHOUSES w on i.warehouse_id = w.warehouse_id " +
            " where w.location_id=:location" +
            " group by product_id) j" +
            " on x.product_id=j.product_id" +
            " group by x.product_id;",nativeQuery = true)
    List<Tuple> searchProductWarehouseTuple(@Param("location")Long location);


    Product findTopByOrderByIdDesc();

    List<Product> findAllByNameAndCategory(String name,Category category);
}
