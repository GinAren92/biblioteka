package com.SkpZSeb.biblioteka.service;

import com.SkpZSeb.biblioteka.libExceptions.UserNotFoundLibException;
import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.repository.BookRepository;
import com.SkpZSeb.biblioteka.repository.UserRepository;

public class ReturnBook {

    public static void bookReturn(Book book, BookRepository bookRepository){
            book.setUser(null);
            book.setRentDate(null);
            bookRepository.save(book);
    }

    public static String startReturn(Long bookId, BookRepository bookRepository, UserRepository userRepository){
        try {
            Book book = bookRepository.findById(bookId).orElseThrow(()->new UserNotFoundLibException("No match for provided book id."));
            if(book.getUser()!=null){
                User user = book.getUser();
                UserStatusUpdate.updateUserInfo(user,userRepository,bookRepository);
                ReturnBook.bookReturn(book,bookRepository);
                return book.getTitle()+" successfully returned.";
            }else return "Book is not rented";

        } catch (UserNotFoundLibException e) {
            return e.getMessage();
        }
    }
}
