package com.tobi.notificationService.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Supplier {

    private String address;
    private String contactNumber;

    private List<Product> product;



}
