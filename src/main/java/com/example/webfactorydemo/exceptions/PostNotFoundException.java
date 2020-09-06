package com.example.webfactorydemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends NotFoundException {
    public PostNotFoundException(ErrorKey errorKey) {
        super(errorKey);
    }
}
