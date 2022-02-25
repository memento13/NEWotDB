package pknu.otDB.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pknu.otDB.dto.CategoryAjaxDto;
import pknu.otDB.entity.Category;
import pknu.otDB.entity.Product;
import pknu.otDB.repository.CategoryRepository;
import pknu.otDB.repository.ProductRepository;
import pknu.otDB.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @GetMapping("/product/category")
    public String searchProductByCategory(Model model){

        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList",categoryList);

        return "product/category";
    }

    @PostMapping("/product/category")
    public String showProductList(Model model, CategoryAjaxDto dto){
        Long id = Long.parseLong(dto.getResult());
        System.out.println("categoryId = " + dto.getResult());
        Category category = categoryRepository.findById(id).get();
        List<Product> productList = productRepository.findAllByCategoryOrderById(category);
        model.addAttribute("productList",productList);

        return "product/category :: #productListView";
    }

    @GetMapping("/product/name")
    public String searchProductByName(Model model){

        List<Product> productList = productRepository.findAllByOrderById();
        model.addAttribute("productList",productList);

        return "product/name";
    }

    @PostMapping("/product/name")
    public String showByNameProductList(Model model, CategoryAjaxDto dto){
        System.out.println("categoryId = " + dto.getResult());

        List<Product> productList = productRepository.findAllByNameOrderById(dto.getResult().toLowerCase());
        model.addAttribute("productList",productList);

        return "product/name :: #productListView";
    }

    @GetMapping("/product/new")
    public String creatForm(Model model){
        model.addAttribute("productForm",new ProductForm());
        model.addAttribute("categoryList",categoryRepository.findAll());

        return "product/createProductForm";
    }

    @PostMapping("/product/new")
    public String create(@Valid ProductForm productForm, BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("categoryList",categoryRepository.findAll());
            return "product/createProductForm";
        }
        productService.createProduct(productForm);
        return "redirect:/";
    }

}
