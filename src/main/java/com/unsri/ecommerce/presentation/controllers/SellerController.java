package com.unsri.ecommerce.presentation.controllers;

import com.unsri.ecommerce.application.behaviours.seller.commands.LoginSeller;
import com.unsri.ecommerce.application.behaviours.seller.commands.LogoutSeller;
import com.unsri.ecommerce.application.behaviours.seller.commands.RegisterSeller;
import com.unsri.ecommerce.application.behaviours.seller.query.GetSellerById;
import com.unsri.ecommerce.application.behaviours.seller.query.VerifySeller;
import com.unsri.ecommerce.domain.models.Seller;
import com.unsri.ecommerce.infrastructure.repository.JwtUserRepository;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;
import com.unsri.ecommerce.infrastructure.webconfig.jwt.JwtUtils;
import com.unsri.ecommerce.presentation.payload.request.LoginRequest;
import com.unsri.ecommerce.presentation.payload.response.JwtResponse;
import com.unsri.ecommerce.presentation.payload.response.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@RestController
public class SellerController extends BaseController{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    JwtUserRepository jwtUserRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/api/v1/login")
    @ResponseBody
    public BaseResponse<JwtResponse> authenticateUser(@RequestHeader("Device-Id") String deviceId, @RequestBody LoginRequest loginRequest) {
        LoginSeller command = new LoginSeller(
            authenticationManager,
            jwtUtils,
            deviceId,
            loginRequest.getUsername(),
            loginRequest.getPassword()
        );
        JwtResponse jwtResponse = command.execute(Optional.empty());

        BaseResponse<JwtResponse> baseResponse = new BaseResponse<>();
        baseResponse.setResult(jwtResponse);
        baseResponse.setStatusCode(HttpStatus.OK.toString().substring(4));
        baseResponse.setMessage("Seller successfully logged in");

        return baseResponse;
    }

    @PostMapping("/api/v1/register")
    @ResponseBody
    public BaseResponse<JwtResponse> registerUser(@RequestHeader("Device-Id") String deviceId, @RequestBody Seller seller, HttpServletRequest request)
        throws UnsupportedEncodingException, MessagingException {
        RegisterSeller command = new RegisterSeller(
            authenticationManager,
            sellerRepository,
            request,
            encoder,
            jwtUtils,
            mailSender,
            seller,
            deviceId
        );

        RegisterResponse registerResponse = command.execute(Optional.empty());

        BaseResponse<JwtResponse> baseResponse = new BaseResponse<>();
        baseResponse.setResult(registerResponse.getJwtResponse());
        baseResponse.setStatusCode(registerResponse.getStatusCode().substring(4));
        baseResponse.setMessage(registerResponse.getMessage());

        return baseResponse;
    }

    @GetMapping("/api/v1/verify")
    public BaseResponse<String> verifyUser(@Param("code") String code)
        throws UnsupportedEncodingException, MessagingException {
        VerifySeller command = new VerifySeller(sellerRepository, code);

        String message = command.execute(Optional.empty());

        BaseResponse<String> baseResponse = new BaseResponse<>();
        baseResponse.setResult(null);
        baseResponse.setStatusCode(HttpStatus.OK.toString().substring(4));
        baseResponse.setMessage(message);

        return baseResponse;
    }

    @PostMapping("/api/v1/logout")
    public BaseResponse<String> logoutUser(@RequestHeader("Authorization") String jwt) {
        LogoutSeller command = new LogoutSeller(jwtUserRepository, jwt.substring(7));

        String message = command.execute(Optional.empty());

        BaseResponse<String> baseResponse = new BaseResponse<>();
        baseResponse.setResult(null);
        baseResponse.setStatusCode(HttpStatus.OK.toString().substring(4));
        baseResponse.setMessage(message);

        return baseResponse;
    }

    @GetMapping("/api/v1/user")
    public  BaseResponse<Seller> getUser(HttpServletRequest request) {
        int sellerId = getAuthorizedUser(request.getUserPrincipal());

        Seller seller = new Seller();
        seller.setId(sellerId);
        GetSellerById getSellerById = new GetSellerById(sellerRepository);
        Seller sellerResponse = getSellerById.execute(Optional.of(seller));

        BaseResponse<Seller> baseResponse = new BaseResponse<>();
        baseResponse.setResult(sellerResponse);
        return baseResponse;
    }

}
