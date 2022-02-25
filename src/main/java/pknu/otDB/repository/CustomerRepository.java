package pknu.otDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pknu.otDB.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByOrderById();

    @Query("select c from Customer c where lower(c.name) like %:name% order by c.id")
    List<Customer> findAllByNameOrderById(@Param("name") String name);
}
