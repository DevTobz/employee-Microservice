package com.tobi.mailingService.Controller;

import com.tobi.mailingService.Model.Employee;
import com.tobi.mailingService.Model.VerificationToken;
import com.tobi.mailingService.Service.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/mail")
public class MailingController {

    @Autowired
    private MailingService mailingService;

    @GetMapping(path = "/sendVerificationLink")
    public String sendVerificationLink(@RequestParam String username,
                                       @RequestParam String token) {
        return mailingService.sendVerificationLink(username,token);
    }

    @GetMapping(path = "/resendVerificationLink")
    public String resendVerificationLink(@RequestParam String username){
      return mailingService.resendVerificationLink(username);
    }

    @GetMapping(path = "/forgotPassword")
    public String sendChangePasswordLink(@RequestParam String username){
        return mailingService.sendChangePasswordLink(username);
    }


}