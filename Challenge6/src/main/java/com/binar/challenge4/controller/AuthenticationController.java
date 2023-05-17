package com.binar.challenge4.controller;

import com.binar.challenge4.DTO.AuthenticationDTO;
import com.binar.challenge4.DTO.RegisterDTO;
import com.binar.challenge4.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/registerAdmin")
    public ResponseEntity<ResponseEntity<String>> registerAdmin(@RequestBody RegisterDTO registerDTO){
        return ResponseEntity.ok(authService.registerAdmin(registerDTO));
    }

    @PostMapping("/registerCustomer")
    public ResponseEntity<ResponseEntity<String>> registerCustomer(@RequestBody RegisterDTO registerDTO){
        return ResponseEntity.ok(authService.registerCustomer(registerDTO));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticatioinResponse> authenticate(@RequestBody AuthenticationDTO authenticationDTO){
        return ResponseEntity.ok(authService.authenticate(authenticationDTO));
    }


}
