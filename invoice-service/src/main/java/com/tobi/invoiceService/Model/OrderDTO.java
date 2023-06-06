package com.tobi.invoiceService.Model;


import java.util.List;

public record OrderDTO(
        int totalPrice,
        List<Product> products,
        PaymentMethod paymentMethod,
        String shippingAddress,
        OrderStatus orderStatus) {


}
