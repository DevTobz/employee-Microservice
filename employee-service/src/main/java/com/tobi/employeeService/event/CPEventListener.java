package com.tobi.employeeService.event;

import com.tobi.employeeService.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CPEventListener implements ApplicationListener<ChangePasswordEvent> {

    private Employee user;
    @Override
    public void onApplicationEvent(ChangePasswordEvent event) {
        user = event.getUser();
        String URL = event.getApplicationURL()+
                "/app/changePassword"+"?username="+
                user.getUsername();
         log.info("Please Click on the link to change your password: " + URL);
    }
}
