package com.SkpZSeb.biblioteka.service;

import com.SkpZSeb.biblioteka.libExceptions.BookNotFoundLibException;
import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.model.BookingRecord;
import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.repository.BookRepository;
import com.SkpZSeb.biblioteka.repository.BookingRepo;
import com.SkpZSeb.biblioteka.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@Service
public class RentService {
    final static int RENTING_PERIOD = 14;
    private final BookingRepo bookingRepo;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public List<BookingRecord> allRentedBooksByUser(User user){
        return bookingRepo.findByUser(user);
    }

    public String rentingBook(User user,Long bookID){
        if(user.getBanExpired() == null){
            Book book = bookRepository.findById(bookID).orElseThrow(()->new BookNotFoundLibException("Book id does`t exist."));

            if(book.getAvailableQty() > 0) {
                BookingRecord bookingRecord = new BookingRecord();
                bookingRecord.setBookId(bookID);
                bookingRecord.setRentDate(LocalDateTime.now());
                bookingRecord.setTitle(book.getTitle());
                bookingRecord.setUser(user);
                bookingRepo.save(bookingRecord);
                book.setAvailableQty(book.getAvailableQty()-1);
                bookRepository.save(book);
                return "Renting " + book.getTitle() + " to " + user.getFirstName() + " " + user.getLastName() + " complited.";
            }else return "No books available";

        }else return "User is blocked to: "+user.getBanExpired();
    }




}
