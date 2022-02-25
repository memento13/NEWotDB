package pknu.otDB.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pknu.otDB.dto.CategoryAjaxDto;
import pknu.otDB.entity.Customer;
import pknu.otDB.entity.Order;
import pknu.otDB.entity.Product;
import pknu.otDB.repository.CustomerRepository;
import pknu.otDB.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @GetMapping("/customer/name")
    public String searchCustomerByName(Model model){

        List<Customer> customerList = customerRepository.findAllByOrderById();

        model.addAttribute("customerList",customerList);

        return "customer/name";
    }

    @PostMapping("/customer/name")
    public String showByNameCustomerList(Model model, CategoryAjaxDto dto){

        List<Customer> customerList = customerRepository.findAllByNameOrderById(dto.getResult().toLowerCase());
        model.addAttribute("customerList",customerList);

        return "customer/name :: #customerListView";
    }

    @GetMapping("/customer/info/{customerId}")
    public String showCustomerInfo(@PathVariable("customerId") Long customerId,Model model){

        Customer customer = customerRepository.findById(customerId).get();
        model.addAttribute("customer",customer);

        List<Order> orderList = orderRepository.findListByCustomerOrderByOrder_dateDesc(customer);
        model.addAttribute("orderList", orderList);

        return "customer/info";
    }

}
