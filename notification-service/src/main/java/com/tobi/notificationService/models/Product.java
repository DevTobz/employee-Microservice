package com.tobi.notificationService.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@RequiredArgsConstructor

public class Product {

    private String name;
    private String description;
    private String category;
    private int price;
    private int quantity;

    private List<Supplier> supplierInfo;
    private Orders orders;

    private String productStockLevel;

    public String getDetails(){
        return "The details include: "+
                " Name: "+getName() +
                " Category: "+ getCategory()+
                " Price: "+ getPrice()+
                " Quantity: " + getQuantity();
    }


}
