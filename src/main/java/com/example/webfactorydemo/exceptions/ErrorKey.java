package com.example.webfactorydemo.exceptions;

public enum ErrorKey {
    InvalidCredentials("Invalid credentials"),
    UserNotFound("User not found"),
    UserAlreadyExists("User already exists"),
    BadRequest("Bad request"),
    PostNotFound("Post not found"),

    ;

    ErrorKey(String message) {
        this.message = message;
    }

    public String message;

}
