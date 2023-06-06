package com.tobi.orderAndProductService.Model.Enitity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;



@Entity
@Data
@RequiredArgsConstructor


public class Product {

    @Id
    @SequenceGenerator(name = "product_sequence"
            ,sequenceName = "product_sequence"
            ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long productId;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private String category;

    @JsonProperty
    private int price;
    @JsonProperty
    private int quantity;


    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "supplierId")
    private List<Supplier> supplierInfo;

    private String productStockLevel = null;

    public String getDetails(){
        return "The details include: "+
                " Name: "+getName() +
                " Category: "+ getCategory()+
                " Price: "+ getPrice()+
                " Quantity: " + getQuantity();
    }


}
