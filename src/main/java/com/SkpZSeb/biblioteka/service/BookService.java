package com.SkpZSeb.biblioteka.service;

import com.SkpZSeb.biblioteka.libExceptions.BookNotFoundLibException;
import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book findById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new BookNotFoundLibException("Not match for bookId: "+bookId));
        return book;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
