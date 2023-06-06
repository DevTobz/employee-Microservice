package com.tobi.customerService.Model.Request;


import com.tobi.customerService.Model.ProductOrdered;
import com.tobi.customerService.Model.OrderStatus;
import com.tobi.customerService.Model.PaymentMethod;
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
