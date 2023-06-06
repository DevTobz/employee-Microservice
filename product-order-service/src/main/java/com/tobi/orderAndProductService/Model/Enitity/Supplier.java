package com.tobi.orderAndProductService.Model.Enitity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Supplier {
    @Id
    @SequenceGenerator(name = "supplier_sequence",
            sequenceName = "supplier_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long supplierId;
    private String address;
    private String contactNumber;
    @ManyToMany(mappedBy = "supplierInfo")
    private List<Product> product;


}
