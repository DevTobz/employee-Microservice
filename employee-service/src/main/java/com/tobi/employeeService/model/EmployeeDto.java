package com.tobi.employeeService.model;

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
