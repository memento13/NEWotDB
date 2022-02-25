package pknu.otDB.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pknu.otDB.controller.ProductForm;
import pknu.otDB.entity.Category;
import pknu.otDB.entity.Product;
import pknu.otDB.repository.CategoryRepository;
import pknu.otDB.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long createProduct(ProductForm productForm){

        Category category = categoryRepository.findOneById(Long.parseLong(productForm.getCategory()));
        validateDuplicateProduct(productForm.getName(),category);
        Long id = createProductId();
        Product product = new Product(id,productForm,category);
        productRepository.save(product);
        return id;
    }

//   제품 이름 중복 확인
    public void validateDuplicateProduct(String name,Category category){
        List<Product> products = productRepository.findAllByNameAndCategory(name,category);
        if(!products.isEmpty()){
            throw new IllegalStateException("같은 카테고리의 중복된 제품 이름은 생성불가");
        }
    }

//    새제품 id 생성
    private Long createProductId(){
        Long id = productRepository.findTopByOrderByIdDesc().getId()+1;
        return id;
    }
}
