package com.tobi.employeeService.service;

import com.tobi.employeeService.model.*;
import com.tobi.employeeService.openFeignClient.MailingClient;
import com.tobi.employeeService.repository.EmployeeRepository;
import com.tobi.employeeService.repository.VerificationTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegisterResponse registerResponse;
    @Autowired
    private static VerificationTokenRepository tokenRepository;

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MailingClient mailingClient;

    public EmployeeService(VerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    //For saving verification token to the database.
    public static void saveToken(Employee user, String token) {
        VerificationToken vt= new VerificationToken(token, user);
        tokenRepository.save(vt);
    }


    //For converting userModel to user and saving user to the database
    public RegisterResponse registerUser(UserModel userModel) {
        Employee user = new Employee().builder().
                email(userModel.getEmail()).
                username(userModel.getUsername()).
                password(passwordEncoder.encode(userModel.getPassword())).
                role(userModel.getRole()).
                permission(userModel.getPermission())
                .build();

        repository.save(user);
        String token= UUID.randomUUID().toString();
        saveToken(user, token);
        String URL = mailingClient.sendVerificationLink(user.getUsername(),token);

        //write a service that will send the verification link URL

        registerResponse.setUrl(URL);
        registerResponse.setEmployee(user);
        registerResponse.setMessage("User save to the database. " + " Please check your mail for the verification link.");
        return registerResponse;



    }

    //Does the service on verification of account
    public RegisterResponse verifyAccount(String token, String username, HttpServletRequest request) {
        Employee user = repository.findByUsername(username);
        VerificationToken Token = tokenRepository.findByToken(token);
        Calendar cal = Calendar.getInstance();
        if(token==null){
            registerResponse.setMessage("Invalid");

            return registerResponse;
        }

        if(Token.getEmployee().equals(user) &&
                (Token.getExpirationTime().getTime()-cal.getTime().getTime()) >=0 &&
                user.getVerificationToken().equals(Token) ){


                user.setVerified(true);
                repository.save(user);
                registerResponse.setMessage("Done");


        }else {
            tokenRepository.delete(Token);

            //Call on the resendVerificationLink

            String url = mailingClient.resendVerificationLink(user.getUsername());
            registerResponse.setUrl(url);
            registerResponse.setMessage("invalid");

        }
        return registerResponse;

    }

    // Does service for the resend Verification link for user
    public RegisterResponse resendLink(String username) {
               Employee user= repository.findByUsername(username);
               if(user==null){
                   registerResponse.setMessage("NotFound");
                   return registerResponse;
               }else if(!user.isVerified()){

                   String token= UUID.randomUUID().toString();

                   saveToken(user, token);

                   String url = mailingClient.sendVerificationLink(user.getUsername(),token);
                   registerResponse.setMessage("NotVerified");
                   registerResponse.setUrl(url);
                   //Call on service to resend verification link URL***
                   return registerResponse;
               }else{
                   registerResponse.setMessage( "Verified");
                   return registerResponse;
               }
    }


    //Does service for forgot password
    public RegisterResponse forgotPassword(ForgotPasswordDetails details) {
        Employee user= repository.findByUsername(details.getUsername());
        if(user!=null){

          String url =  mailingClient.sendChangePasswordLink(user.getUsername());
            registerResponse.setMessage("Sent");
            registerResponse.setUrl(url);
        }else{
            registerResponse.setMessage("NotFound");
        }
        return registerResponse;
    }

    public String changePassword(ChangePasswordDetails details, String username) {
        Employee user= repository.findByUsername(username);
        if(user.getPassword().equals(details.getNewPassword())){
            return "Error";

        }else{
            user.setPassword(passwordEncoder.encode(details.getNewPassword()));
            repository.save(user);
            return "DONE";
        }

    }

    public EmployeeDto getEmployeeByUsername(String username) {
        Employee employee = repository.findByUsername(username);

        EmployeeDto employeeDto = new EmployeeDto(employee.getUsername(), employee.getPassword(), employee.getRole(),employee.getPermission());

        return employeeDto;

    }
}
