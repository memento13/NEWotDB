package pknu.otDB.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import pknu.otDB.controller.ProductForm;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    private String description;
    private BigDecimal standard_cost;
    private BigDecimal list_price;

//    카테고리
//   json 반환시 문제가 있는데 이걸 self참조처럼 따로 반환값 지정이 필요할듯
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItem> orderItemList = new ArrayList<>();

    @OneToMany(mappedBy = "id.product",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Inventory> inventoryList = new ArrayList<>();

    public Product(Long id, ProductForm productForm,Category category) {
        this.id = id;
        this.name = productForm.getName();
        this.description = productForm.getDescription();
        this.standard_cost = new BigDecimal(productForm.getStandard_cost());
        this.list_price = new BigDecimal(productForm.getList_price());
        this.category = category;
    }
}
