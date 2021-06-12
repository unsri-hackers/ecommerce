package com.unsri.ecommerce.application.behaviours;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface BaseCommand<T> {
    public T execute(Optional<T> param)
        throws UnsupportedEncodingException, MessagingException;
}
