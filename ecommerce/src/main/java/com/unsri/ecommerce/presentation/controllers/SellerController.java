package com.unsri.ecommerce.presentation.controllers;

import com.unsri.ecommerce.application.behaviours.seller.commands.LoginSeller;
import com.unsri.ecommerce.infrastructure.security.jwt.JwtUtils;
import com.unsri.ecommerce.presentation.payload.request.LoginRequest;
import com.unsri.ecommerce.presentation.payload.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/api/v1/login")
    @ResponseBody
    public BaseResponse<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        LoginSeller command = new LoginSeller(
            authenticationManager,
            jwtUtils,
            loginRequest.getUsername(),
            loginRequest.getPassword()
        );
        JwtResponse jwtResponse = command.execute(java.util.Optional.empty());

        BaseResponse<JwtResponse> baseResponse = new BaseResponse<>();
        baseResponse.setResult(jwtResponse);
        baseResponse.setStatusCode(HttpStatus.OK.toString());
        baseResponse.setMessage("Vendor successfully logged in");

        return baseResponse;
    }
}
