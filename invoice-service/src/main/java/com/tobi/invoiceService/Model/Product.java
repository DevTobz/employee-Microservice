package com.tobi.invoiceService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    private String name;
    private String description;
    private String category;
    private int price;
    private int quantity;


    private List<Supplier> supplierInfo;
    private Orders orders;

    private String productStockLevel;

}
