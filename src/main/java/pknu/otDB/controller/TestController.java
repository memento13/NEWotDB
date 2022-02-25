package pknu.otDB.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pknu.otDB.entity.Customer;
import pknu.otDB.entity.OrderItem;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
