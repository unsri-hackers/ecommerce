package com.unsri.ecommerce.application.behaviours.seller.commands;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.Seller;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;
import com.unsri.ecommerce.infrastructure.security.jwt.JwtUtils;
import com.unsri.ecommerce.infrastructure.security.service.SellerDetailsImpl;
import com.unsri.ecommerce.presentation.payload.response.JwtResponse;
import com.unsri.ecommerce.presentation.payload.response.RegisterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class RegisterSeller implements BaseCommand<RegisterResponse> {

    private final AuthenticationManager authenticationManager;
    private final SellerRepository sellerRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final Seller seller;

    public RegisterSeller(
        AuthenticationManager authenticationManager,
        SellerRepository sellerRepository,
        PasswordEncoder encoder,
        JwtUtils jwtUtils,
        Seller seller
    ) {
        this.authenticationManager = authenticationManager;
        this.sellerRepository = sellerRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.seller = seller;
    }

    @Override
    public RegisterResponse execute(Optional<RegisterResponse> param) {
        String email = seller.getEmail();
        String password = seller.getPassword();

        if (sellerRepository.existsByEmail(email)) {
            return new RegisterResponse(
                null,
                "Email is already in use",
                HttpStatus.CONFLICT.toString()
            );
        }

        // Create new seller's account
        seller.setPassword(encoder.encode(password));
        sellerRepository.save(seller);

        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwt(authentication);

        SellerDetailsImpl sellerDetails = (SellerDetailsImpl) authentication.getPrincipal();

        return new RegisterResponse(
            new JwtResponse(
                jwt,
                sellerDetails.getId(),
                sellerDetails.getUsername(),
                sellerDetails.getEmail()
            ),
            "Seller is registered successfully",
            HttpStatus.OK.toString()
        );
    }
}
