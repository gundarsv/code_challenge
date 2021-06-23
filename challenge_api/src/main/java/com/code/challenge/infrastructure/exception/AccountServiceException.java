package com.code.challenge.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class AccountServiceException extends RuntimeException{
    public AccountServiceException() {
        super();
    }
    public AccountServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public AccountServiceException(String message) {
        super(message);
    }
    public AccountServiceException(Throwable cause) {
        super(cause);
    }
}
