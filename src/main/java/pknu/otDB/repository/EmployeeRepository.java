package pknu.otDB.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pknu.otDB.dto.EmployeeDto;
import pknu.otDB.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("select new pknu.otDB.dto.EmployeeDto(e.id, e.first_name, e.last_name, e.email, e.phone, e.hire_date, e.manager.id, e.job_title) from Employee e order by e.id")
    List<EmployeeDto> findAllDto();




}
