package com.SkpZSeb.biblioteka.controller;

import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.repository.BookRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
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

    @PutMapping("/book{id}/rent-date-pass-30d")
    public String rentDateMinus30Days(@Valid @PathVariable(value="id") Long bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Unexpected bookID"));

        book.setRentDate(LocalDateTime.now().minusDays(30));
        bookRepository.save(book);

        return "Current rent date in book: "+book.getTitle()+" is: "+book.getRentDate();
    }
    @GetMapping("/all-books")
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
}
