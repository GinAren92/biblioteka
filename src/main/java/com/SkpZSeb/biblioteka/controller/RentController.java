package com.SkpZSeb.biblioteka.controller;


import com.SkpZSeb.biblioteka.libExceptions.UserNotFoundLibException;
import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.repository.BookRepository;
import com.SkpZSeb.biblioteka.repository.UserRepository;
import com.SkpZSeb.biblioteka.service.RentService;
import com.SkpZSeb.biblioteka.service.UserStatusUpdate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public RentController(final UserRepository userRepository, final BookRepository bookRepository){
        this.userRepository=userRepository;
        this.bookRepository=bookRepository;
    }

    @GetMapping("/user{userId}")
    public List<Book> getAllRentedBooks(@PathVariable(value = "userId") Long userId) throws UserNotFoundLibException {
        if(UserStatusUpdate.validUserId(userId,userRepository)){
            User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundLibException("User Id not Found"));
            UserStatusUpdate.updateUserInfo(user,userRepository,bookRepository);
            return RentService.allRentedBooksByUser(userId, bookRepository);
        }else throw new UserNotFoundLibException("User Id not Found");
    }

    @PutMapping("/user{userId}/book{bookId}")
    public String rentingBook(@PathVariable(value="userId") Long userId,@PathVariable(value="bookId") Long bookId)
            throws UserNotFoundLibException {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundLibException("User Id not Found"));
        UserStatusUpdate.updateUserInfo(user,userRepository,bookRepository);
        return RentService.rentingBook(user,bookRepository, bookId);
    }


}
