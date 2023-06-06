package com.tobi.loginService.OpenFeign;

import com.tobi.loginService.Models.Others.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service", path = "/Employee")

public interface EmployeeClient {
    @GetMapping(path = "getEmployee/{username}")
    public EmployeeDto getEmployeeByUsername(@PathVariable("username") String username);
}
