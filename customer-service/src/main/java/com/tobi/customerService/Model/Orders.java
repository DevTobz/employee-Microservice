package com.tobi.customerService.Model;


import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    private int totalPrice;

    private List<ProductOrdered> productOrderedList;

    private String customerUsername;

    private OrderStatus orderStatus;

    private PaymentMethod paymentMethod;

    private String shippingAddress;

}
