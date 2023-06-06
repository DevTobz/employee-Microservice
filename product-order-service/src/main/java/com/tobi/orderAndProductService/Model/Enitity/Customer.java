package com.tobi.orderAndProductService.Model.Enitity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String address;

    private String username;

    private List<Orders> orders;

}
