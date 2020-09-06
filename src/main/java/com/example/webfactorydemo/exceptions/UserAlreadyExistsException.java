package com.example.webfactorydemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends Exception {
    UserAlreadyExistsException(ErrorKey errorKey) {
        super(errorKey.message);
    }
}
