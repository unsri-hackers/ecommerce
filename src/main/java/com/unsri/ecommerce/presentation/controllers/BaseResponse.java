package com.unsri.ecommerce.presentation.controllers;

public class BaseResponse<T> {
    private T result;
    private String statusCode;
    private String message;

    public BaseResponse() {}

    public BaseResponse(T result, String statusCode, String message) {
        this.result = result;
        this.statusCode = statusCode;
        this.message = message;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "result=" + result +
                ", statusCode='" + statusCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
