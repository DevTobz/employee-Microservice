package com.tobi.orderAndProductService.Model.Request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequest {
    private String name;
    private String description;
    private String category;
    private int price;
    private int quantity;
}
