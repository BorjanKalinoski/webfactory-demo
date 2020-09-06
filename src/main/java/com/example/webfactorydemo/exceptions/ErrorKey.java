package com.example.webfactorydemo.exceptions;

public enum ErrorKey {
    InvalidCredentials("Invalid credentials"),
    UserDoesNotExist("User does not exist"),
    UserAlreadyExists("User already exists"),
    BadRequest("Invalid input")
    ;

    ErrorKey(String message) {
        this.message = message;
    }

    public String message;

}
