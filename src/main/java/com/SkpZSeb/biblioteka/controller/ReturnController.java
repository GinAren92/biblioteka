package com.SkpZSeb.biblioteka.controller;

import com.SkpZSeb.biblioteka.repository.BookRepository;
import com.SkpZSeb.biblioteka.repository.UserRepository;
import com.SkpZSeb.biblioteka.service.ReturnBook;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/return")
public class ReturnController {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ReturnController(final UserRepository userRepository, final BookRepository bookRepository){
        this.userRepository=userRepository;
        this.bookRepository=bookRepository;
    }

    @PutMapping("/book{id}")
    public String returnBook(@Valid @PathVariable(value = "id") Long bookId){
        return ReturnBook.startReturn(bookId,bookRepository,userRepository);
    }
}
