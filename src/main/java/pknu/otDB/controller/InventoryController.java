package pknu.otDB.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pknu.otDB.dto.CategoryAjaxDto;
import pknu.otDB.dto.ProductWarehouseDto;
import pknu.otDB.entity.*;
import pknu.otDB.repository.*;
import pknu.otDB.service.InventoryService;

import javax.persistence.Tuple;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class InventoryController {

    private final ProductRepository productRepository;
    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;
    private final LocationRepository locationRepository;
    private final InventoryService inventoryService;
    private final WarehouseRepository warehouseRepository;

    @GetMapping("/inventory/location")
    public String showInventory(Model model){

        int regionSelected = 1;
        int countrySelected = 1;
        List<ProductWarehouseDto> inventoryList = new ArrayList<>();
        model.addAttribute("regionSelected",regionSelected);
        model.addAttribute("countrySelected",countrySelected);
        model.addAttribute("inventoryList",inventoryList);

        List<Region> regionList = regionRepository.findAll();
        model.addAttribute("regionList",regionList);
        List<Country> countryList = new ArrayList<>();
        model.addAttribute("countryList",countryList);
        List<Location> locationList = new ArrayList<>();
        model.addAttribute("locationList",locationList);

        Long location_id = null;
        model.addAttribute("location_id",location_id);

        return "inventory/location";
    }

    @PostMapping("/inventory/location/region")
    public String showCountryList(Model model, CategoryAjaxDto region){
        Long id = Long.parseLong(region.getResult());
        Region findRegion = regionRepository.findById(id).get();
        int regionSelected = 0;
        int countrySelected = 1;
        model.addAttribute("countrySelected",countrySelected);
        List<Country> countryList = countryRepository.findAllByRegion(findRegion);

        model.addAttribute("regionSelected",regionSelected);
        model.addAttribute("countryList",countryList);
//        ---------------
        List<Location> locationList = new ArrayList<>();
        model.addAttribute("locationList",locationList);
        List<ProductWarehouseDto> inventoryList = new ArrayList<>();
        model.addAttribute("inventoryList",inventoryList);

        Long location_id = null;
        model.addAttribute("location_id",location_id);

//        return "inventory/location :: #countries";
        return "inventory/location :: #country_form";
    }

    @PostMapping("/inventory/location/country")
    public String showLocationList(Model model, CategoryAjaxDto country){
        String id = String.valueOf(country.getResult());
        Country findCountry = countryRepository.findOneById(id);
        List<Location> locationList = locationRepository.findAllByCountry(findCountry);
        int countrySelected = 0;

        model.addAttribute("countrySelected",countrySelected);
        model.addAttribute("locationList",locationList);
//        ------------
        List<ProductWarehouseDto> inventoryList = new ArrayList<>();
        model.addAttribute("inventoryList",inventoryList);

        Long location_id = null;
        model.addAttribute("location_id",location_id);

//        return "inventory/location :: #locations";
        return "inventory/location :: #location_form";
    }

    @PostMapping("/inventory/location/location")
    public String showInventoryList(Model model, CategoryAjaxDto location){
        Long id = Long.parseLong(location.getResult());
        model.addAttribute("location_id",id);

//        -------------서비스로 빠져야할 부분-----------------
        List<Tuple> tuples = productRepository.searchProductWarehouseTuple(id);
        List<ProductWarehouseDto> inventoryList = new ArrayList<>();

//       스트림에 대해 더 알아봐야할듯...
//        분명 필터를 사용하는 방법이 있을텐데
        for (Tuple tuple : tuples) {
            if(tuple.get(5)==null){
                inventoryList.add(new ProductWarehouseDto(tuple.get(0, BigDecimal.class),tuple.get(1,String.class),tuple.get(2,BigDecimal.class),tuple.get(3,BigDecimal.class),tuple.get(4,String.class),new BigDecimal("0")));
            }
            else{
                inventoryList.add(new ProductWarehouseDto(tuple.get(0,BigDecimal.class),tuple.get(1,String.class),tuple.get(2,BigDecimal.class),tuple.get(3,BigDecimal.class),tuple.get(4,String.class),tuple.get(5,BigDecimal.class)));
            }
        }
//        -------------서비스로 빠져야할 부분-----------------
        model.addAttribute("inventoryList",inventoryList);


        return "inventory/location :: #inventoryListView";
    }

    @GetMapping("/inventory/location/product")
    public String showInventoryLocationProduct(Model model, @RequestParam(value = "product_id",required = true) Long productId,@RequestParam(value = "location_id",required = true) Long locationId){

        List<Inventory> inventoryList = inventoryService.findLocationAndProductById(locationId, productId);
        model.addAttribute("inventoryList",inventoryList);

        Location location = locationRepository.findById(locationId).get();
        model.addAttribute("location",location);
        Product product = productRepository.findById(productId).get();
        model.addAttribute("product",product);

        return "inventory/product";
    }

    @GetMapping("/inventory/new")
    public String createForm(Model model){

        List<Product> productList = productRepository.findAllByOrderById();
        model.addAttribute("productList",productList);
        Long productFirstId = productList.get(0).getId();
        model.addAttribute("productFirstId",productFirstId);

        List<Warehouse> warehouseList = warehouseRepository.findAllByOrderById();
        model.addAttribute("warehouseList",warehouseList);
        Long warehouseFirstId = warehouseList.get(0).getId();
        model.addAttribute("warehouseFirstId",warehouseFirstId);

        model.addAttribute("inventoryForm",new InventoryForm());

        return "inventory/createInventoryForm";
    }

    @PostMapping("/inventory/new")
    public String create(@Valid InventoryForm inventoryForm, BindingResult result,Model model){

        if(result.hasErrors()){
            List<Product> productList = productRepository.findAllByOrderById();
            model.addAttribute("productList",productList);
            Long productFirstId = productList.get(0).getId();
            model.addAttribute("productFirstId",productFirstId);

            List<Warehouse> warehouseList = warehouseRepository.findAllByOrderById();
            model.addAttribute("warehouseList",warehouseList);
            Long warehouseFirstId = warehouseList.get(0).getId();
            model.addAttribute("warehouseFirstId",warehouseFirstId);

            model.addAttribute("inventoryForm",new InventoryForm());

            return "inventory/createInventoryForm";
        }
        inventoryService.createInventory(inventoryForm);
        return "redirect:/";
    }
}
