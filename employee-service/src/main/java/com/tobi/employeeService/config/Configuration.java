package com.tobi.employeeService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@org.springframework.context.annotation.Configuration
public class Configuration {

    /*private static String[] White_List = {"/register",
            "/verifyAccount",
            "/resendVerificationLink",
            "/forgotPassword",
            "/changePassword"};*/
    @Bean
    public PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
        http.
                csrf().
                disable().authorizeHttpRequests().
                requestMatchers("/Employee/**").
                permitAll();
      return  http.build();
    }


}
