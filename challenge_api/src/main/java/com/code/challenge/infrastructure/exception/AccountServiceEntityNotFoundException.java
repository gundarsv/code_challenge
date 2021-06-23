package com.code.challenge.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AccountServiceEntityNotFoundException extends RuntimeException{
    public AccountServiceEntityNotFoundException() {
        super();
    }
    public AccountServiceEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public AccountServiceEntityNotFoundException(String message) {
        super(message);
    }
    public AccountServiceEntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
