package com.tobi.mailingService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String username;
    private String password;
    private String email;


    private Role role;

    private Permission permission;
    private boolean verified = false;


    private VerificationToken verificationToken;

}
