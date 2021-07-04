package com.unsri.ecommerce.infrastructure.mediator;

public interface Mediator<TResponse, TRequest> {
    TResponse send(TRequest request, Handler handler);
}