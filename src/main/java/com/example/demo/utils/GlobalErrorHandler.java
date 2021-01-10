package com.example.demo.utils;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DataNotFoundError.class)
    public String resourceNotFoundException(DataNotFoundError ex, WebRequest request) {
        return " "+HttpStatus.NOT_FOUND;
    }

    @ExceptionHandler(Exception.class)
    public String globalExceptionHandler(Exception ex, WebRequest request) {
        return " "+HttpStatus.NOT_FOUND;
    }

}
