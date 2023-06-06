package com.tobi.mailingService.Model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@RequiredArgsConstructor
public class VerificationToken {
    private static final int ExpiryTime = 10;
    private String token;
    private Date expirationTime;

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
