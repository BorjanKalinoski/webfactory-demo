package com.example.webfactorydemo.exceptions;

import com.example.webfactorydemo.models.ErrorResponseDto;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(value = {UserDoesNotExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleException(UserDoesNotExistException e) {
        return new ErrorResponseDto(e.getMessage());
    }

    @ExceptionHandler(value = {InvalidCredentialsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleException(InvalidCredentialsException e) {
        return new ErrorResponseDto(e.getMessage());
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleException(Exception e) {
        Exception newException = new BadRequestException(ErrorKey.BadRequest);
//        if (e instanceof DataIntegrityViolationException) { PSQL- user already registered
//            String msg = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
//        }
        return new ErrorResponseDto(newException.getMessage());
    }

}
