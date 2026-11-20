package com.restorant_springdemo.restorant.exception;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private BundleMessageService bundleMessageService;

@Autowired
    public GlobalExceptionHandler(BundleMessageService bundleMessageService) {
        this.bundleMessageService = bundleMessageService;
    }

    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse handleSystemException(SystemException exception) {

        return bundleMessageService.getMessage(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<MessageResponse> handleValidationException(
            MethodArgumentNotValidException exception) {

        List<MessageResponse> messageResponses = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError ->
                        bundleMessageService.getMessage(
                                fieldError.getDefaultMessage()
                        )
                )
                .toList();

        return messageResponses;
    }
}
