package com.SkpZSeb.biblioteka.service;

import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.repository.BookRepository;
import lombok.Getter;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
@Getter
public class RentService {
    final static int RENTING_PERIOD = 14;

    public static List<Book> allRentedBooksByUser(Long userId, BookRepository bookRepository){
        List<Book> rentedBooks = (List<Book>) bookRepository.findAll().stream()
                .filter(b -> b.getUser().getId() == userId);
        return rentedBooks;
    }

    public static String rentingBook(User user, BookRepository bookRepository,Long bookID){
        if(user.getBanExpired() == null){
            Book book = bookRepository.findById(bookID).orElseThrow(()->new ResourceNotFoundException("Book id does`t exist."));
            if(book.getUser() == null) {
                book.setRentDate(LocalDateTime.now());
                book.setUser(user);
                bookRepository.save(book);
                return "Renting " + book.getTitle() + " to " + user.getFirstName() + " " + user.getLastName() + " complited.";
            }else return "Book already rented, try another book.";
            }else return "User is blocked to: "+user.getBanExpired();
    }




}
