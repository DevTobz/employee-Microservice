package com.tobi.orderAndProductService.Model.Request;

import com.tobi.orderAndProductService.Model.OrderStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderUpdateRequest {
    private OrderStatus orderStatus;

}
