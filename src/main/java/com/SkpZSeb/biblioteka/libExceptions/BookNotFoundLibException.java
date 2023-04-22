package com.SkpZSeb.biblioteka.libExceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class BookNotFoundLibException extends RuntimeException {

    public BookNotFoundLibException(String ex){
        super(ex);
    }
}
