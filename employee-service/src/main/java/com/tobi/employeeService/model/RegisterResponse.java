package com.tobi.employeeService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterResponse {
    private String url;
    private  String message;

    private Employee employee;

}
