package com.tobi.customerService.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class CustomerRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String username;
}
