package com.unsri.ecommerce.presentation.controllers.response;

public class RegisterResponse {

    private JwtResponse jwtResponse;
    private String message;
    private String statusCode;

    public RegisterResponse(JwtResponse jwtResponse, String message, String statusCode) {
        this.jwtResponse = jwtResponse;
        this.message = message;
        this.statusCode = statusCode;
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

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

}
