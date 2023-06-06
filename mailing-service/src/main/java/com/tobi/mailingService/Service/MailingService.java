package com.tobi.mailingService.Service;

import com.tobi.mailingService.Model.Employee;
import com.tobi.mailingService.Model.VerificationToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailingService {
    @Autowired
    private HttpServletRequest request;

    public String sendVerificationLink(String username, String token) {

        String URL =  "http://localhost:8080"+
                "/Employee/verifyAccount"+"?token="+
                token+
                "&username="+
                username;

        return URL;

    }

    public String resendVerificationLink(String username) {
        String URL =  "http://localhost:8080"+
                "/Employee/resendVerificationLink?username=" +
                username;
        return URL;
    }

    public String sendChangePasswordLink(String username) {
        String URL = "http://localhost:8080" +
                "/Employee/changePassword"+"?username="+
                username;
        return URL;
    }
}
