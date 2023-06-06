package com.tobi.employeeService.openFeignClient;

import com.tobi.employeeService.model.Employee;
import com.tobi.employeeService.model.VerificationToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mailing-service",path = "/mail")
public interface MailingClient {
    @GetMapping(path = "/sendVerificationLink")
    public String sendVerificationLink(@RequestParam("username") String username,
                                       @RequestParam("token") String token);


    @GetMapping(path = "/resendVerificationLink")
    public String resendVerificationLink(@RequestParam("username") String username);

    @GetMapping(path = "/forgotPassword")
    public String sendChangePasswordLink(@RequestParam("username") String username);
}
