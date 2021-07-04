package com.unsri.ecommerce.application.behaviours.seller.commands;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.application.domain.Seller;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;
import com.unsri.ecommerce.presentation.webconfig.jwt.JwtUtils;
import com.unsri.ecommerce.presentation.webconfig.service.SellerDetailsImpl;
import com.unsri.ecommerce.presentation.controllers.response.JwtResponse;
import com.unsri.ecommerce.presentation.controllers.response.RegisterResponse;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;

public class RegisterSeller implements BaseCommand<RegisterResponse> {

    private final AuthenticationManager authenticationManager;
    private final SellerRepository sellerRepository;
    private final HttpServletRequest request;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final JavaMailSender mailSender;
    private final Seller seller;
    private final String deviceId;
    private String jwt;

    public RegisterSeller(
        AuthenticationManager authenticationManager,
        SellerRepository sellerRepository,
        HttpServletRequest request,
        PasswordEncoder encoder,
        JwtUtils jwtUtils,
        JavaMailSender mailSender,
        Seller seller,
        String deviceId
    ) {
        this.authenticationManager = authenticationManager;
        this.sellerRepository = sellerRepository;
        this.request = request;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.mailSender = mailSender;
        this.seller = seller;
        this.deviceId = deviceId;
    }

    @Override
    public RegisterResponse execute(Optional<RegisterResponse> param)
        throws UnsupportedEncodingException, MessagingException {
        String email = seller.getEmail();
        String password = seller.getPassword();

        if (sellerRepository.existsByEmail(email)) {
            return new RegisterResponse(
                null,
                "Email is already in use",
                HttpStatus.CONFLICT.toString()
            );
        }

        saveSellerToDb(password);

        SellerDetailsImpl sellerDetails = authenticateSeller(email, password);

        jwtUtils.saveJwtUser(jwt, deviceId, sellerDetails);

        sendVerificationEmail(seller, getSiteUrl());

        return new RegisterResponse(
            new JwtResponse(
                jwt,
                sellerDetails.getId(),
                sellerDetails.getUsername(),
                sellerDetails.getEmail()
            ),
            "Seller is registered successfully. Check your email to activate your account",
            HttpStatus.OK.toString()
        );
    }

    private void saveSellerToDb(String password) {
        seller.setPassword(encoder.encode(password));
        seller.setInventories(new ArrayList<>());
        seller.setIsActivated(false);
        seller.setVerificationCode(RandomString.make(64));
        sellerRepository.save(seller);
    }

    private SellerDetailsImpl authenticateSeller(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        jwt = jwtUtils.generateJwt(authentication);

        return (SellerDetailsImpl) authentication.getPrincipal();
    }

    private void sendVerificationEmail(Seller seller, String siteUrl)
        throws MessagingException, UnsupportedEncodingException {
        String toAddress = seller.getEmail();
        String fromAddress = "deuvoxecommerce@gmail.com";
        String senderName = "Deuvox";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
            + "Please click the link below to verify your registration:<br>"
            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY HERE</a></h3>"
            + "Thank you,<br>"
            + "Deuvox.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", seller.getFirstName() + " " + seller.getLastName());
        String verifyUrl = siteUrl + "/api/v1/verify?code=" + seller.getVerificationCode();
        content = content.replace("[[URL]]", verifyUrl);

        helper.setText(content, true);

        mailSender.send(message);
    }

    private String getSiteUrl() {
        String siteUrl = request.getRequestURL().toString();
        return siteUrl.replace(request.getServletPath(), "");
    }

}
