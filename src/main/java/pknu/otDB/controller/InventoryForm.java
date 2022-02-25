package pknu.otDB.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class InventoryForm {

    private String product;
    private String warehouse;
    @NotEmpty(message = "등록할 제품의 수량을 입력하세요")
    private String quantity;
}
