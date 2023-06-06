package com.tobi.orderAndProductService.Controllers;

import com.tobi.orderAndProductService.Model.Enitity.Orders;
import com.tobi.orderAndProductService.Model.Enitity.ProductOrdered;
import com.tobi.orderAndProductService.Model.Request.OrderRequest;
import com.tobi.orderAndProductService.Model.Request.OrderUpdateRequest;
import com.tobi.orderAndProductService.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Inventory")
public class OrderController {

    @Autowired
    private OrdersService service;

    @PostMapping(path = "/Orders/saveOrder")
    public ResponseEntity<String> saveOrderFromService(@RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok(service.saveOrderFromService(orderRequest));
    }


    @GetMapping(path = "/Orders/AllOrders")
    public List<Orders> getOrders(){
        List<Orders> ordersList = service.getAllOrders();
        return ordersList;
    }

    @GetMapping(path = "/Orders/PendingOrders")
    public ResponseEntity<List<Orders>> getAllPendingOrders(){
        return ResponseEntity.ok(service.getAllPendingOrders());
    }

    @PutMapping(path = "/Orders/UpdateOrder")
    public ResponseEntity<String> updateOrderById(@RequestBody OrderUpdateRequest request,
                                                  @RequestParam Long orderId){
      return  ResponseEntity.ok(service.updateOrderById(request,orderId));
    }

    @PostMapping(path = "/Orders/saveProductOrdered")
    public void saveProductOrdered(@RequestBody ProductOrdered productOrdered){
        service.saveProductOrdered(productOrdered);
    }
}
