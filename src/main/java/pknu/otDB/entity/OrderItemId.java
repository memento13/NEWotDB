package pknu.otDB.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class OrderItemId implements Serializable {

    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @NotNull
    private Order order;


}
