package com.tobi.loginService.Service;

import com.tobi.loginService.Models.Others.EmployeeDto;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@NoArgsConstructor
public class EmployeeUserDetails implements org.springframework.security.core.userdetails.UserDetails {

    @Autowired
    private EmployeeDto employeeDto;

    public EmployeeUserDetails(EmployeeDto employeeDto){
        this.employeeDto = employeeDto;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(employeeDto.getRole().name()));
        list.add(new SimpleGrantedAuthority(employeeDto.getPermission().name()));
        return null;
    }

    @Override
    public String getPassword() {

        return employeeDto.getPassword();
    }

    @Override
    public String getUsername() {

        return employeeDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
