package com.tobi.orderAndProductService.Model.Enitity;

import com.tobi.orderAndProductService.Model.OrderStatus;
import com.tobi.orderAndProductService.Model.PaymentMethod;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Orders {
    @Id
    @SequenceGenerator(name = "order_sequence",
                    sequenceName = "order_sequence",
                    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long orderId;
    private int totalPrice;

   @OneToMany(cascade = CascadeType.ALL,
           mappedBy = "orders",
   fetch = FetchType.EAGER)
   private List<ProductOrdered> productOrderedList;


    private String customerUsername;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String shippingAddress;


}
