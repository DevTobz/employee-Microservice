package com.tobi.orderAndProductService.Model;
import com.tobi.orderAndProductService.Model.Enitity.Customer;
import com.tobi.orderAndProductService.Model.Enitity.Orders;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Invoice {
    private Orders orders;
    private Customer customer;


}
