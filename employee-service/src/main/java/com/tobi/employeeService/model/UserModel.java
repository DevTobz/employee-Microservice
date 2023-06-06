package com.tobi.employeeService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserModel {
    private String username;
    private String password;
    private String email;
    private Role role;
    private Permission permission;

}
