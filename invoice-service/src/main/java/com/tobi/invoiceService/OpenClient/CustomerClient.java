package com.tobi.invoiceService.OpenClient;

import com.tobi.invoiceService.Model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "customer-service", path = "/app")
public interface CustomerClient {

    @GetMapping(path ="/getCustomer")
    public Customer getCustomer(@RequestParam("userName") String userName);
}
