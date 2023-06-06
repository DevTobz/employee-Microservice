package com.tobi.employeeService.event;

import com.tobi.employeeService.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@Component
public class RCEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private Employee user;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Construct the token
        Employee user=event.getUser();
        String token= UUID.randomUUID().toString();

        //Send Token to user through logging
        String URL= event.getApplicationURL()+
                "/app/verifyAccount"+"?token="+
                token+
                "&username="+
                user.getUsername();

        log.info("Please the click on the verification link to verify your account:"+URL);
    }
}
