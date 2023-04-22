package com.SkpZSeb.biblioteka.libExceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundLibException extends RuntimeException {

    public UserNotFoundLibException(String ex){
        super(ex);
    }
}
