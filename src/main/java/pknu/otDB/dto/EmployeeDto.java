package pknu.otDB.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pknu.otDB.entity.Employee;

import java.time.LocalDateTime;

@Data
public class EmployeeDto {

    private Long id;

    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private LocalDateTime hire_date;

    private Long manager;

    private String job_title;

//    private String password;


    public EmployeeDto(Long id, String first_name, String last_name, String email, String phone, LocalDateTime hire_date, Long manager, String job_title) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.hire_date = hire_date;
        this.manager = manager;
        this.job_title = job_title;
    }

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.first_name = employee.getFirst_name();
        this.last_name = employee.getLast_name();
        this.email = employee.getEmail();
        this.phone = employee.getPhone();
        this.hire_date = employee.getHire_date();
        if(employee.getManager()==null){
            this.manager = employee.getId();
        }else {
            this.manager = employee.getManager().getId();
        }
        this.job_title = employee.getJob_title();
    }

}
