package com.tobi.orderAndProductService.OpenFeign;

import com.tobi.orderAndProductService.Model.DTO.InvoiceDTO;
import com.tobi.orderAndProductService.Model.Enitity.Orders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "invoice-service", path = "/Invoice")
public interface InvoiceClient {
    @PostMapping(path = "/generateInvoice")
    public InvoiceDTO generateInvoice(@RequestBody Orders orders);
}
