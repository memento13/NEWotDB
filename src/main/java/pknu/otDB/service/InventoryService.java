package pknu.otDB.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pknu.otDB.controller.InventoryForm;
import pknu.otDB.entity.Inventory;
import pknu.otDB.entity.InventoryId;
import pknu.otDB.entity.Product;
import pknu.otDB.entity.Warehouse;
import pknu.otDB.repository.InventoryRepository;
import pknu.otDB.repository.ProductRepository;
import pknu.otDB.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public List<Inventory> findLocationAndProductById(Long locationId,Long productId){

        List<Inventory> result = inventoryRepository.searchAllByLocationAndProduct(locationId, productId);
        return result;

    }

    @Transactional
    public void createInventory(InventoryForm inventoryForm){
//        해당하는 인벤토리 id 만들기
        Product product = productRepository.findById(Long.parseLong(inventoryForm.getProduct())).get();
        Warehouse warehouse = warehouseRepository.findById(Long.parseLong(inventoryForm.getWarehouse())).get();
        InventoryId id = new InventoryId(product,warehouse);

//        form에 있는 수량 int로 변환
        int quantityInput = Integer.parseInt(inventoryForm.getQuantity());

//        동일한 inventoryId 를 가지고 있는 객체가 있는지 찾기
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);

//        해당 창고에 물건이 존재할 때
        if(optionalInventory.isPresent()){
            Inventory inventory = optionalInventory.get();
            int quantity = quantityCalc(quantityInput,inventory.getQuantity());
            inventory.setQuantity(quantity);
            inventoryRepository.save(inventory);
        }
//        해당 창고에 존재 안할때
        else{
            int quantity = quantityCalc(quantityInput,0);
            Inventory inventory = new Inventory(id,quantity);
            inventoryRepository.save(inventory);
        }
    }

    private int quantityCalc(int quantityInput,int stored){
        int quantity = quantityInput+stored;
        if(quantity<0){
            throw new IllegalStateException("기존 재고와 등록할 재고의 수량이 음수인 경우 등록할 수  없습니다.");
        }
        else {
            return quantity;
        }
    }
}
