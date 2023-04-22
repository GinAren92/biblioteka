package com.SkpZSeb.biblioteka.controller;

import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.repository.BookRepository;
import com.SkpZSeb.biblioteka.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PostMapping("/add-book")
    public String addBook(@Valid @RequestBody Book book){

            bookService.save(book);
            return "Title: "+book.getTitle()+", save completed.";

    }

    @PutMapping("/book{id}/rent-date-pass-30d")
    public String rentDateMinus30Days(@Valid @PathVariable(value="id") Long bookId){
        Book book = bookService.findById(bookId);

        book.setRentDate(LocalDateTime.now().minusDays(30));
        bookService.save(book);

        return "Current rent date in book: "+book.getTitle()+" is: "+book.getRentDate();
    }
    @GetMapping("/all-books")
    public List<Book> getBooks(){
        return bookService.findAll();
    }
}
