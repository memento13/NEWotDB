package pknu.otDB.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pknu.otDB.dto.EmployeeDto;
import pknu.otDB.entity.Employee;
import pknu.otDB.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/employee/findAll")
    public String findAllEmployee(Model model, @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        Page<Employee> employees = employeeRepository.findAll(pageable);
        Page<EmployeeDto> employeeList = employees.map(employee -> new EmployeeDto(employee));


        int startPage = Math.max(1,employeeList.getPageable().getPageNumber()-3);
        int endPage = Math.min(employeeList.getTotalPages(),employeeList.getPageable().getPageNumber()+4);
        if(employeeList.getPageable().getPageNumber()<4 && employeeList.getTotalPages()>7){
            endPage = 7;
        }


        model.addAttribute("employeeList",employeeList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "employee/findAll";
    }

}
