package com.unsri.ecommerce.presentation.controllers;

public class BaseResponse<T> {
    private T result;
    private String statusCode;
    private String message;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
