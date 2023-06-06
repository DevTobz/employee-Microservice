package com.tobi.customerService.Model.DTO;


import com.tobi.customerService.Model.OrderStatus;
import com.tobi.customerService.Model.PaymentMethod;
import com.tobi.customerService.Model.Product;

import java.util.List;

public record OrderDTO(
        int totalPrice,
        List<Product> products,
        PaymentMethod paymentMethod,
        String shippingAddress,
        OrderStatus orderStatus) {


}
