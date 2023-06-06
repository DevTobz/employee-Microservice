package com.tobi.loginService.Service;

import com.tobi.loginService.Models.Others.EmployeeDto;
import com.tobi.loginService.OpenFeign.EmployeeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private EmployeeClient client;
    public EmployeeUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       // Employee user = repository.findByUsername(username);
        EmployeeDto user = client.getEmployeeByUsername(username);
        EmployeeUserDetails userDetails = new EmployeeUserDetails(user);
        return userDetails;
    }
}

