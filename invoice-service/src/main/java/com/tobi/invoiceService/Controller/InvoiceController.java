package com.tobi.invoiceService.Controller;

import com.tobi.invoiceService.Model.InvoiceDTO;
import com.tobi.invoiceService.Model.Orders;
import com.tobi.invoiceService.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @PostMapping(path = "/generateInvoice")
    public InvoiceDTO generateInvoice(@RequestBody Orders orders){
        return service.generateInvoice(orders);
    }


}
