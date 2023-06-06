package com.tobi.customerService.OpenFeign;

import com.tobi.customerService.Model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-order-service", path = "/Inventory")
public interface ProductClient {

    @GetMapping("{productName}")
    public Product viewProduct(@PathVariable("productName") String name);

    @GetMapping(path = "/Product/getProduct")
    public Product getProduct(@RequestParam("productName") String productName);

    @PutMapping(path = "Product/saveProduct")
    public ResponseEntity<String> updateProductFromService(@RequestBody Product productToBeSaved);
}
