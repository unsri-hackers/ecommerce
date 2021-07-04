package com.unsri.ecommerce.infrastructure.mediator;

public interface Handler<TRequest, TResponse> {
    TResponse handle(TRequest request);
}
