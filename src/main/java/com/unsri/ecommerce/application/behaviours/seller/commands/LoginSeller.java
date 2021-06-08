package com.unsri.ecommerce.application.behaviours.seller.commands;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.JwtUser;
import com.unsri.ecommerce.infrastructure.repository.JwtUserRepository;
import com.unsri.ecommerce.infrastructure.security.jwt.JwtUtils;
import com.unsri.ecommerce.infrastructure.security.service.SellerDetailsImpl;
import com.unsri.ecommerce.presentation.payload.response.JwtResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LoginSeller implements BaseCommand<JwtResponse> {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final String deviceId;
    private final String email;
    private final String password;

    public LoginSeller(
        AuthenticationManager authenticationManager,
        JwtUtils jwtUtils,
        String deviceId,
        String email,
        String password
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.deviceId = deviceId;
        this.email = email;
        this.password = password;
    }

    @Override
    public JwtResponse execute(Optional<JwtResponse> param) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwt(authentication);

        SellerDetailsImpl sellerDetails = (SellerDetailsImpl) authentication.getPrincipal();

        jwtUtils.saveJwtUser(jwt, deviceId, sellerDetails);

        return new JwtResponse(
            jwt,
            sellerDetails.getId(),
            sellerDetails.getUsername(),
            sellerDetails.getEmail()
        );
    }
}
