package com.tobi.loginService.Controller;

import com.tobi.loginService.Models.AuthenticationRequest;
import com.tobi.loginService.Models.AuthenticationResponse;
import com.tobi.loginService.Service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Login")
public class Controller {
    @Autowired
    private AuthenticateService service;

    @GetMapping(path = "/Inventory")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request){

        return ResponseEntity.ok(service.authLogin(request));

    }

    @GetMapping(path = "/AdminPage")
    public ResponseEntity<String> adminPage(@RequestParam String username){


        return ResponseEntity.ok(service.homepage(username));
    }

}
