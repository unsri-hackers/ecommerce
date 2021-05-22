package com.unsri.ecommerce.application.behaviours;

import java.util.Optional;

public interface BaseCommand<T> {
    public T execute(Optional<T> param);
}
