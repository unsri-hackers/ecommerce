package com.unsri.ecommerce.application.behaviours;

public interface BaseCommand<T> {
    public T execute();
}
