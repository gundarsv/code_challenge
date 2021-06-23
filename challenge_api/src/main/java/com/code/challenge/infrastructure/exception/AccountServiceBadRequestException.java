package com.code.challenge.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AccountServiceBadRequestException extends RuntimeException{
    public AccountServiceBadRequestException() {
        super();
    }
    public AccountServiceBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    public AccountServiceBadRequestException(String message) {
        super(message);
    }
    public AccountServiceBadRequestException(Throwable cause) {
        super(cause);
    }
}
