package com.tobi.orderAndProductService.Model.DTO;


import com.tobi.orderAndProductService.Model.Enitity.Product;
import com.tobi.orderAndProductService.Model.PaymentMethod;

import java.util.List;

public record OrderDTO(
        int totalPrice,
        List<Product> products,
        PaymentMethod paymentMethod,
        String shippingAddress) {


}
