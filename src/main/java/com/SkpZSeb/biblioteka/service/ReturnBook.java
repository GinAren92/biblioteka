package com.SkpZSeb.biblioteka.service;

import com.SkpZSeb.biblioteka.libExceptions.UserNotFoundLibException;
import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.model.BookingRecord;
import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.repository.BookingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ReturnBook {

    private final UserStatusUpdate userStatusUpdate;

    public void bookReturn(BookingRecord bookingRecord, Book book, BookService bookService, BookingRepo bookingRepo){
            bookingRecord.setReturnDate(LocalDateTime.now());
            book.setAvailableQty(book.getAvailableQty()+1);
            bookService.save(book);
            bookingRepo.save(bookingRecord);
    }

    public String startReturn(Long userId, Long bookId, BookService bookService, UserService userService, BookingRepo bookingRepo){
        try {
            Book book = bookService.findById(bookId);
            User user = userService.findById(userId);
                BookingRecord bookingRecord = bookingRepo.findByUserAndBookId(user,bookId);
                userStatusUpdate.updateUserInfo(user,userService,bookService);
                bookReturn(bookingRecord,book,bookService,bookingRepo);
                return book.getTitle()+" successfully returned.";

        } catch (UserNotFoundLibException e) {
            return e.getMessage();
        }
    }
}
