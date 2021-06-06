package com.unsri.ecommerce.presentation.payload.response;

import com.unsri.ecommerce.infrastructure.security.jwt.JwtUtils;

public class RegisterResponse {

    private JwtResponse jwtResponse;
    private String message;
    private String code;

    public RegisterResponse(JwtResponse jwtResponse, String message, String code) {
        this.jwtResponse = jwtResponse;
        this.message = message;
        this.code = code;
    }

    public JwtResponse getJwtResponse() {
        return jwtResponse;
    }

    public void setJwtResponse(JwtResponse jwtResponse) {
        this.jwtResponse = jwtResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
