package com.binar.challenge4.service;

import com.binar.challenge4.DTO.AuthenticationDTO;
import com.binar.challenge4.DTO.RegisterDTO;
import com.binar.challenge4.config.JwtService;
import com.binar.challenge4.controller.AuthenticatioinResponse;
import com.binar.challenge4.model.User;
import com.binar.challenge4.model.UserRole;
import com.binar.challenge4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<String> register(RegisterDTO registerDTO) {
        var user = User.builder()
                .username(registerDTO.getUsername())
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .userRole(UserRole.ADMIN)
                .build();
        userRepository.save(user);
        return ResponseEntity.ok("Berhasil Registrasi");
        /*var jwtToken = jwtService.generateToken(user);
        return AuthenticatioinResponse.builder()
                .token(jwtToken)
                .build();*/
    }

    public AuthenticatioinResponse authenticate(AuthenticationDTO authenticationDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDTO.getEmail(),
                        authenticationDTO.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticationDTO.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatioinResponse.builder()
                .token(jwtToken)
                .build();
    }
}
