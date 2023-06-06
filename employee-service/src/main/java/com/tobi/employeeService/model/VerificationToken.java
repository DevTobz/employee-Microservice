package com.tobi.employeeService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {


    private static final int ExpiryTime = 10;
    @Id
    @SequenceGenerator(name = "verifyToken_sequence",
            sequenceName = "verifyToken_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String token;
    private Date expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "employeeId")
    private Employee employee;

    public VerificationToken(String token, Employee employee){
        super();
        this.employee = employee;
        this.token = token;
        this.expirationTime = calculateExpirationDate(ExpiryTime);

    }

    public Date calculateExpirationDate(int Expiry_Time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, ExpiryTime);
        return new  Date(calendar.getTime().getTime());
    }

}
