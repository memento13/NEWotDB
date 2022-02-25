package pknu.otDB.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDER_ITEMS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class OrderItem {

    @EmbeddedId
    private OrderItemId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @NotNull
    private Product product;

    private int quantity;

    private BigDecimal unit_price;
//    getUnitPrice를 따로 만들어야 할듯...



}
