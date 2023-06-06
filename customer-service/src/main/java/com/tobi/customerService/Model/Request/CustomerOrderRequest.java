package com.tobi.customerService.Model.Request;

import com.tobi.customerService.Model.PaymentMethod;
import com.tobi.customerService.Model.ProductOrdered;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderRequest {
    private List<ProductOrdered> productOrderedList;

    private PaymentMethod paymentMethod;

    private String shippingAddress;
}
