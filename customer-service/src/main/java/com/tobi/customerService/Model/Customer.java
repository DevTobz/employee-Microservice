package com.tobi.customerService.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @SequenceGenerator(name="user_sequence",
                sequenceName = "user_sequence",
                allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String username;
}

