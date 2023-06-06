package com.tobi.loginService.Models.Others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

   private String username;
  private String password;
   private Role role;
    private Permission permission;
}
