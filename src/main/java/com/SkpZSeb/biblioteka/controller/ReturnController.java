package com.SkpZSeb.biblioteka.controller;

import com.SkpZSeb.biblioteka.repository.BookingRepo;
import com.SkpZSeb.biblioteka.service.BookService;
import com.SkpZSeb.biblioteka.service.ReturnBook;
import com.SkpZSeb.biblioteka.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/return")
public class ReturnController {
    private final UserService userService;
    private final BookService bookService;
    private final BookingRepo bookingRepo;
    private final ReturnBook returnBook;
    @PutMapping("/user{userId}book{bookId}")
    public String returnBook(@Valid @PathVariable Long userId, @PathVariable Long bookId){
        return returnBook.startReturn(userId,bookId,bookService,userService,bookingRepo);
    }
}
