package com.SkpZSeb.biblioteka.controller;


import com.SkpZSeb.biblioteka.libExceptions.UserNotFoundLibException;
import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.model.BookingRecord;
import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.service.BookService;
import com.SkpZSeb.biblioteka.service.RentService;
import com.SkpZSeb.biblioteka.service.UserService;
import com.SkpZSeb.biblioteka.service.UserStatusUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent")
public class RentController {

    private final UserService userService;
    private final BookService bookService;
    private final RentService rentService;
    private final UserStatusUpdate userStatusUpdate;


    @GetMapping("/user{userId}")
    public List<BookingRecord> getAllRentedBooks(@PathVariable(value = "userId") Long userId) throws UserNotFoundLibException {
        if(userService.validUserId(userId)){
            User user = userService.findById(userId);
            userStatusUpdate.updateUserInfo(user,userService,bookService);
            return rentService.allRentedBooksByUser(user);
        }else throw new UserNotFoundLibException("User Id not Found");
    }

    @PutMapping("/user{userId}/book{bookId}")
    public String rentingBook(@PathVariable(value="userId") Long userId,@PathVariable(value="bookId") Long bookId)
            throws UserNotFoundLibException {
        User user = userService.findById(userId);
        userStatusUpdate.updateUserInfo(user,userService,bookService);
        return rentService.rentingBook(user, bookId);
    }


}
