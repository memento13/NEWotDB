package pknu.otDB.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Order {

    @Id
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @NotNull
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salesman_id", referencedColumnName = "employee_id")
    @NotNull
    private Employee salesMan;

    private LocalDateTime order_date;

    @OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItem> orderItemIdList = new ArrayList<>();

    public BigDecimal getTotalPrice(){
        List<OrderItem> orderItemList = this.getOrderItemIdList();
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            BigDecimal totalItemPrice = orderItem.getUnit_price();
            totalItemPrice = totalItemPrice.multiply(new BigDecimal(orderItem.getQuantity()));
            totalPrice = totalPrice.add(totalItemPrice);
        }
        return totalPrice;
    }

}
