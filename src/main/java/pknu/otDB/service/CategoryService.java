package pknu.otDB.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pknu.otDB.controller.CategoryForm;
import pknu.otDB.entity.Category;
import pknu.otDB.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;


//    카테고리 생성
    @Transactional
    public Long createCategory(CategoryForm form){

//        validateDuplicateCategory(form);
        Long id = createCategoryId();
        Category category = new Category(id,form.getName());
        categoryRepository.save(category);
        return id;
    }

//    카테고리 중복 확인
    public void validateDuplicateCategory(CategoryForm form){
        List<Category> findCategories = categoryRepository.findAllByName(form.getName());
        if(!findCategories.isEmpty()){
            throw new IllegalStateException("중복된 카테고리 이름은 생성불가");
        }
    }
//    카테고리 id값
    private Long createCategoryId(){
        Category lastCategory = categoryRepository.findTopByOrderByIdDesc();
        return lastCategory.getId()+1;
    }


}
