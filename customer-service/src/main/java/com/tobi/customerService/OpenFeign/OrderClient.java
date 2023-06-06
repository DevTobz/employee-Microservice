package com.tobi.customerService.OpenFeign;

import com.tobi.customerService.Model.Orders;
import com.tobi.customerService.Model.ProductOrdered;
import com.tobi.customerService.Model.Request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-order-service",path = "/Inventory")
public interface OrderClient {

    @PostMapping(path = "Orders/saveOrder")
    public ResponseEntity<String> saveOrderFromService(@RequestBody OrderRequest ordersRequest);

    @PostMapping(path = "/Orders/saveProductOrdered")
    public void saveProductOrdered(@RequestBody ProductOrdered productOrdered);


}

