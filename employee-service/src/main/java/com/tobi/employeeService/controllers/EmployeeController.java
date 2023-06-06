package com.tobi.employeeService.controllers;

import com.tobi.employeeService.model.*;
import com.tobi.employeeService.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/Employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private HttpServletRequest request;



    //REGISTER
    @PostMapping(path = "/register")
    public String RegisterUser(@RequestBody UserModel userModel){
      RegisterResponse response=  service.registerUser(userModel);
    return response.getMessage() + response.getUrl();
    }

    //VERIFY ACCOUNT
    @GetMapping(path="/verifyAccount")
    public String verifyAccount(@RequestParam String token, @RequestParam String username ){
        RegisterResponse result= service.verifyAccount(token,username,request);
        if(result.getMessage().equalsIgnoreCase("Done")){
            return "USER VERIFICATION SUCCESSFUL";
        }else if(result.getMessage().equalsIgnoreCase("invalid")){
            return "FALSE TOKEN OR TOKEN EXPIRED."+
                    " Please click the link to resend verification link. " +
                    result.getUrl();
        }else{
            return "TOKEN IS EMPTY";
        }


    }

    //SEND VERIFICATION LINK
    @GetMapping(path = "/resendVerificationLink")
    public String resendVerificationLink(@RequestParam String username){

       RegisterResponse response= service.resendLink(username);
        if(response.getMessage().equalsIgnoreCase("NotFound")){
            return "User does not exist in the database. "+ " Please register again..";
        }if(response.getMessage().equalsIgnoreCase("NotVerified")){
            return "Check your mail for the verification link. "+ response.getUrl();
        }else{
            return"User "+ username + " is already verified";
        }
    }

    //FORGOT PASSWORD
    @GetMapping(path = "/forgotPassword")
    public String forgotPassword(@RequestBody ForgotPasswordDetails details){
       RegisterResponse response= service.forgotPassword(details);
        if(response.getMessage().equalsIgnoreCase("Sent")){
            return "The Verification Link has been sent to your email. " +
                    response.getUrl();
        }else{
            return "User was not found in the database";
        }

    }

    @GetMapping(path = "/changePassword")
    public String changePassword(@RequestBody ChangePasswordDetails details,
                               @RequestParam String username){
        String done= service.changePassword(details,username);
        if(done.equalsIgnoreCase("Error")){
            return "Password Changed Successfully";
        }else{
            return "An error occurred while saving new password." +
                    " Please enter a different password";
        }

    }

    @GetMapping(path = "getEmployee/{username}")
    public EmployeeDto getEmployeeByUsername(@PathVariable("username") String username){
        return service.getEmployeeByUsername(username);

    }


    // the applicationURL constructor for event
    private String getApplicationURL(HttpServletRequest request) {
        return "http://"+
                request.getServerName()+
                ":"+
                request.getServerPort()+
                request.getContextPath();
    }

}
