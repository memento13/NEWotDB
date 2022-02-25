package pknu.otDB.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data

public class ProductWarehouseDto {

    private Long id;
    private String name;
    private BigDecimal standard_cost;
    private BigDecimal list_price;
    private String category_name;
    private Integer quantity;

    public ProductWarehouseDto(BigDecimal id, String name, BigDecimal standard_cost, BigDecimal list_price, String category_name, BigDecimal quantity) {
        this.id = id.longValue();
        this.name = name;
        this.standard_cost = standard_cost;
        this.list_price = list_price;
        this.category_name = category_name;
        if(quantity == null){
            this.quantity = 0;
        }
        else {
            this.quantity = quantity.intValue();
        }
    }
}
