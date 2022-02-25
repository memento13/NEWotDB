package pknu.otDB.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pknu.otDB.entity.Order;
import pknu.otDB.repository.OrderRepository;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    @GetMapping("/order/orderInfo")
    public String showOrderInfo(@RequestParam(value = "orderId",required = true) Long orderId, Model model){

        System.out.println("orderId = " + orderId);

        Order order = orderRepository.findById(orderId).get();
        model.addAttribute("order",order);

        return "order/orderInfo";
    }
}
