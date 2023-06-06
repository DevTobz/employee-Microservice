package com.tobi.employeeService.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @SequenceGenerator(name="employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long employeeId;

    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Permission permission;
    private boolean verified = false;

    @OneToOne(mappedBy = "employee")
    private VerificationToken verificationToken;


}
