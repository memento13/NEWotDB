package pknu.otDB.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pknu.otDB.service.CategoryService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category/new")
    public String createForm(Model model){
        model.addAttribute("categoryForm", new CategoryForm());
        return "category/createCategoryForm";
    }

    @PostMapping("/category/new")
    public String create(@Valid CategoryForm categoryForm, BindingResult result, Model model){
        System.out.println("categoryForm = " + categoryForm.getName());

        if(result.hasErrors()){
            return "category/createCategoryForm";
        }
//        카테고리 중복 체크
        categoryService.validateDuplicateCategory(categoryForm);
//        통과시 생성
        categoryService.createCategory(categoryForm);
        return "redirect:/";
    }
}
