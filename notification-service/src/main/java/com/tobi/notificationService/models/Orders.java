package com.tobi.notificationService.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@RequiredArgsConstructor
public class Orders {
    private int totalPrice;

    private List<Product> productList;

    private Customer customer;

    private OrderStatus orderStatus;


    private PaymentMethod paymentMethod;

    private String shippingAddress;


}
