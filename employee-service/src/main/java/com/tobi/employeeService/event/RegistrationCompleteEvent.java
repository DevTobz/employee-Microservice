package com.tobi.employeeService.event;

import com.tobi.employeeService.model.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent{

    private Employee user;
    private String ApplicationURL;
    public RegistrationCompleteEvent(Employee user, String ApplicationURL) {
        super(user);
        this.ApplicationURL=ApplicationURL;
        this.user=user;
    }
}
