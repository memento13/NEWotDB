package pknu.otDB.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CategoryForm {

    @NotEmpty(message = "등록할 카테고리 이름을 입력하세요")
    private String name;
}
