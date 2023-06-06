package com.tobi.loginService.Models;

import lombok.Data;
import lombok.RequiredArgsConstructor;



@Data
@RequiredArgsConstructor

 public class AuthenticationResponse {
    private String token;
    private String message;
}
