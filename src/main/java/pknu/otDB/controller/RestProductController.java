package pknu.otDB.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pknu.otDB.dto.ProductWarehouseDto;
import pknu.otDB.entity.*;
import pknu.otDB.repository.*;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RestProductController {


    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final LocationRepository locationRepository;

    @GetMapping("/rest/product/{id}")
    public List<Product> restFindByCategory(@PathVariable("id") Long id){
        Category category = categoryRepository.findById(id).get();
        List<Product> productList = productRepository.findAllByCategoryOrderById(category);

        return productList;
    }

    // fetch lazy 로딩때문에 작동 안함
    @GetMapping("/rest/order/{id}")
    public List<Order> restOrderFindById(@PathVariable("id") Long id){

        Customer customer = customerRepository.findById(id).get();
        List<Order> orderList = orderRepository.findListByCustomerOrderByOrder_dateDesc(customer);

        return orderList;
    }

    @GetMapping("/rest/inventory/location/{id}")
    public List<ProductWarehouseDto> restInventoryFindByLocation(@PathVariable("id") Long id){
        List<Tuple> tuples = productRepository.searchProductWarehouseTuple(id);
        List<ProductWarehouseDto> inventoryList = new ArrayList<>();

//       스트림에 대해 더 알아봐야할듯...
//        분명 필터를 사용하는 방법이 있을텐데
        for (Tuple tuple : tuples) {
            if(tuple.get(5)==null){
                inventoryList.add(new ProductWarehouseDto(tuple.get(0,BigDecimal.class),tuple.get(1,String.class),tuple.get(2,BigDecimal.class),tuple.get(3,BigDecimal.class),tuple.get(4,String.class),new BigDecimal("0")));
            }
            else{
                inventoryList.add(new ProductWarehouseDto(tuple.get(0,BigDecimal.class),tuple.get(1,String.class),tuple.get(2,BigDecimal.class),tuple.get(3,BigDecimal.class),tuple.get(4,String.class),tuple.get(5,BigDecimal.class)));
            }
        }
        return inventoryList;
    }
}
