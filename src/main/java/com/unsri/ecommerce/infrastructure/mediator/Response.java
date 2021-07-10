package com.unsri.ecommerce.infrastructure.mediator;

public class Response<T, TEnumResponseCode> {

    private T _response;
    private TEnumResponseCode _serverStatusCode;
    private String _errorMessage;

    public Response(T _response, TEnumResponseCode statusCode, String errorMessage) {
        this._response = _response;
        this._serverStatusCode = statusCode;
        this._errorMessage = errorMessage;
    }

    public T get_response() {
        return _response;
    }

    public void set_response(T _response) {
        this._response = _response;
    }

    public TEnumResponseCode get_serverStatusCode() {
        return _serverStatusCode;
    }

    public void set_serverStatusCode(TEnumResponseCode _serverStatusCode) {
        this._serverStatusCode = _serverStatusCode;
    }

    public String get_errorMessage() {
        return _errorMessage;
    }

    public void set_errorMessage(String _errorMessage) {
        this._errorMessage = _errorMessage;
    }

    @Override
    public String toString() {
        return "Response{" +
                "_response=" + _response +
                ", _serverStatusCode=" + _serverStatusCode +
                ", _errorMessage='" + _errorMessage + '\'' +
                '}';
    }
}
