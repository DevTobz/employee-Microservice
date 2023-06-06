package com.tobi.customerService.Controller;

import com.tobi.customerService.Model.Customer;
import com.tobi.customerService.Model.Request.CustomerOrderRequest;
import com.tobi.customerService.Model.Request.CustomerRequest;
import com.tobi.customerService.Model.Request.OrderRequest;
import com.tobi.customerService.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/app")
public class CustomerController {

    @Autowired
    private CustomerService service;
    @PostMapping(path = "/CreateOrder")
    public void createOrders(@RequestParam String username,
                                   @RequestBody CustomerOrderRequest request){

        service.createOrder(username,request);
    }

    @PostMapping(path = "/CreateAccount")
    public String createCustomer (@RequestBody CustomerRequest request){

        String back = service.createCustomer(request);
        if(back.equalsIgnoreCase("Done")){
            return "Customer saved successfully";
        }
        return "An error was found while saving customer";
    }

    @GetMapping(path ="/getCustomer")
    public Customer getCustomer(@RequestParam String userName){
        return service.getCustomerByUsername(userName);
    }



}
