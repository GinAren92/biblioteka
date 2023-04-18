package com.SkpZSeb.biblioteka.service;

import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.repository.BookRepository;
import lombok.Getter;

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

    public static String rentingBook(User user, BookRepository bookRepository){
        LocalDateTime now = LocalDateTime.now();
        if(user.getBanExpired() == null){
            List<Book> rentedBooks = allRentedBooksByUser(user.getId(), bookRepository);

        }
    }




}
