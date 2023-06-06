package com.tobi.orderAndProductService.Controllers;

import com.tobi.orderAndProductService.Model.Enitity.Product;
import com.tobi.orderAndProductService.Model.Request.ProductRequest;
import com.tobi.orderAndProductService.Model.Request.ProductUpdateRequest;
import com.tobi.orderAndProductService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Inventory")
public class ProductControllers {
    @Autowired
    private ProductService service;

    @PostMapping(path = "/Product/CreateProduct")
    public ResponseEntity<String> addProduct
            (@RequestBody ProductRequest request){
        return ResponseEntity.ok(service.addProduct(request));
    }

    @PutMapping(path = "/Product/edit/{productName}")
    public ResponseEntity<String> editProduct(@RequestBody ProductUpdateRequest details,
                                              @PathVariable("productName") String name){
        return ResponseEntity.ok(service.updateProduct(details,name));
    }

    @GetMapping(path = "/Product/AllProducts")
    public ResponseEntity<List<Product>> viewAllProduct(){
        return ResponseEntity.ok(service.viewAllProduct());

    }

    @GetMapping("{productName}")
    public Product viewProduct(@PathVariable("productName") String name){
        return service.viewProduct(name);
    }

    @GetMapping(path = "/Product/getProduct")
    public Product getProduct(@RequestParam String productName){
        return service.getProductByName(productName);
    }

    @DeleteMapping("{productName}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productName") String name){
        return ResponseEntity.ok(service.deleteProduct(name));
    }

     @PutMapping(path = "Product/saveProduct")
     public void updateProductFromService(@RequestBody Product productToBeSaved){
         service.updateProductFromService(productToBeSaved);
     }
}
