package com.tobi.orderAndProductService.Model.Request;

import com.tobi.orderAndProductService.Model.Enitity.ProductOrdered;
import com.tobi.orderAndProductService.Model.PaymentMethod;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class OrderRequest {


    private List<ProductOrdered> productOrderedList;

    private PaymentMethod paymentMethod;

    private String shippingAddress;

    private String customerUsername;

}
