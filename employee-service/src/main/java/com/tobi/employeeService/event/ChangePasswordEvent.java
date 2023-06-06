package com.tobi.employeeService.event;

import com.tobi.employeeService.model.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class ChangePasswordEvent extends ApplicationEvent {

    private Employee user;
    private String ApplicationURL;
    public ChangePasswordEvent(Employee user, String ApplicationURL) {
        super(user);
        this.user=user;
        this.ApplicationURL=ApplicationURL;
    }
}
