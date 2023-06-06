package com.tobi.orderAndProductService.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {
    private int price;
    private int quantity;
}

