package com.tobi.loginService.Models.Others;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String username;
    private String password;
    private Role role;
    private Permission permission;




}
