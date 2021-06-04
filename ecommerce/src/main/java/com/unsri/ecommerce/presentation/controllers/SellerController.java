package com.unsri.ecommerce.presentation.controllers;

import com.unsri.ecommerce.application.behaviours.seller.commands.LoginSeller;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;
import com.unsri.ecommerce.infrastructure.security.jwt.JwtUtils;
import com.unsri.ecommerce.presentation.payload.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/v1/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        LoginSeller command = new LoginSeller(
            authenticationManager,
            jwtUtils,
            loginRequest.getUsername(),
            loginRequest.getPassword()
        );
        return command.execute(java.util.Optional.empty());
    }
}
