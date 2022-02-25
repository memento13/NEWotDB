package pknu.otDB.controller;

import lombok.Getter;
import lombok.Setter;
import pknu.otDB.entity.Category;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductForm {
    @NotEmpty(message = "등록할 제품 이름을 입력하세요")
    private String name;
    @NotEmpty(message = "등록할 제품 설명을 입력하세요")
    private String description;
    @NotEmpty(message = "등록할 제품 스탠다드 코스트를 입력하세요")
    private String standard_cost;
    @NotEmpty(message = "등록할 제품 리스트 프라이스를 입력하세요")
    private String list_price;
    private String category;
}
