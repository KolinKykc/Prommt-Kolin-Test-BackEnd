package com.prommt.kolintest.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String exception) {
        super(exception);
    }
}