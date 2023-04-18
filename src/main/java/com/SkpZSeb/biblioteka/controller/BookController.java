package com.SkpZSeb.biblioteka.controller;

import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(final BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @PostMapping("/add-book")
    public String addBook(@Valid @RequestBody Book book){
        try{
            bookRepository.save(book);
            return "Title: "+book.getTitle()+", save completed.";
        }catch(Exception e){
            return e.getStackTrace().toString();
        }
    }

    @GetMapping("/all-books")
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
}
