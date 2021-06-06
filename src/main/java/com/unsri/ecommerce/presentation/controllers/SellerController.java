package com.unsri.ecommerce.presentation.controllers;

import com.unsri.ecommerce.application.behaviours.seller.commands.LoginSeller;
import com.unsri.ecommerce.application.behaviours.seller.commands.RegisterSeller;
import com.unsri.ecommerce.domain.models.Seller;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;
import com.unsri.ecommerce.infrastructure.security.jwt.JwtUtils;
import com.unsri.ecommerce.infrastructure.security.service.SellerDetailsServiceImpl;
import com.unsri.ecommerce.presentation.payload.request.LoginRequest;
import com.unsri.ecommerce.presentation.payload.response.JwtResponse;
import com.unsri.ecommerce.presentation.payload.response.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class SellerController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    SellerDetailsServiceImpl sellerDetailsServiceImpl;

    @Autowired
    PasswordEncoder encoder;

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
        JwtResponse jwtResponse = command.execute(Optional.empty());

        BaseResponse<JwtResponse> baseResponse = new BaseResponse<>();
        baseResponse.setResult(jwtResponse);
        baseResponse.setStatusCode(HttpStatus.OK.toString());
        baseResponse.setMessage("Seller successfully logged in");

        return baseResponse;
    }

    @PostMapping("/api/v1/register")
    @ResponseBody
    public BaseResponse<JwtResponse> registerUser(@RequestBody Seller seller, HttpServletRequest request) {
        RegisterSeller command = new RegisterSeller(
            authenticationManager,
            sellerRepository,
            encoder,
            jwtUtils,
            seller
        );

        RegisterResponse registerResponse = command.execute(Optional.empty());

        BaseResponse<JwtResponse> baseResponse = new BaseResponse<>();
        baseResponse.setResult(registerResponse.getJwtResponse());
        baseResponse.setStatusCode(registerResponse.getCode());
        baseResponse.setMessage(registerResponse.getMessage());

        return baseResponse;
    }

}
