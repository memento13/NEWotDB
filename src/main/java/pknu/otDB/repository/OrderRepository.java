package pknu.otDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pknu.otDB.entity.Customer;
import pknu.otDB.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.customer = :customer order by o.order_date desc ")
    List<Order> findListByCustomerOrderByOrder_dateDesc(@Param("customer") Customer customer);

}
