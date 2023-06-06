package com.tobi.loginService.Service;

import com.tobi.loginService.Models.AuthenticationRequest;
import com.tobi.loginService.Models.AuthenticationResponse;
import com.tobi.loginService.Models.Others.EmployeeDto;
import com.tobi.loginService.OpenFeign.EmployeeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class AuthenticateService {


    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmployeeClient client;


    public AuthenticationResponse authLogin(AuthenticationRequest request) {
        //Call employee Service to get employee
        EmployeeDto employee = client.getEmployeeByUsername(request.getUsername());
        EmployeeUserDetails userDetails = new EmployeeUserDetails(employee);
        String tk = jwtService.generateToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(tk);
        response.setMessage("Login Successful.. Yeaaaiii");

        return response;

        /*if(request.getPassword()!= null && employee.getPassword().equals(request.getPassword())){
            EmployeeUserDetails userDetails = new EmployeeUserDetails(employee);
            String tk = jwtService.generateToken(userDetails);
            AuthenticationResponse response = new AuthenticationResponse();
            response.setToken(tk);
            response.setMessage("Login Successful.. Yeaaaiii");

            return response;

        }else{
            AuthenticationResponse errorResponse = new AuthenticationResponse();
            errorResponse.setMessage("PASSWORD IS EMPTY OR INVALID PASSWORD");
            return errorResponse;
        }*/

    }

    public String homepage(String username) {

        //Call employee service to get employee
        EmployeeDto employeeDto = client.getEmployeeByUsername(username);
        if(employeeDto.getRole().name().equalsIgnoreCase("Admin")){
            return "YOU ARE AUTHORIZED TO VIEW THIS PAGE";
        }return "FUCK OUTTA HERE MEHN. YOU DON'T HAVE THE PERMISSION TO VIEW. HAHAHAHA";
    }
}

