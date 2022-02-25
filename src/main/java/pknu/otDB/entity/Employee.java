package pknu.otDB.entity;


import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EMPLOYEES")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Employee {

    @Id
    @Column(name = "employee_id")
    private Long id;

    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private LocalDateTime hire_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
    @NotNull
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Employee manager;

    private String job_title;


    @ColumnDefault("0")
    @NotNull
    private String password;

    @OneToMany(mappedBy = "manager",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employeeList = new ArrayList<>();

    @OneToMany(mappedBy = "salesMan",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orderList = new ArrayList<>();

}

