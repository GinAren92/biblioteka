package com.SkpZSeb.biblioteka.service;

import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.repository.BookRepository;
import com.SkpZSeb.biblioteka.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public class UserStatusUpdate {

    public static void updateUserInfo(User user, UserRepository userRepository, BookRepository bookRepository){
        List<Book> rentedBooks = RentService.allRentedBooksByUser(user.getId(), bookRepository);
        int newPoints = penaltyPointsCalc(rentedBooks);
        if(newPoints>0) {
            int actualPoints;
            user.setDateLastPoints(LocalDateTime.now());
            if(user.getPenaltyPoints() == null) actualPoints = 0;
            else actualPoints = user.getPenaltyPoints();
            int updatedPoints = newPoints+actualPoints;
            if(updatedPoints>10){
                int tmp = updatedPoints/10;
                LocalDateTime banDateExpired = LocalDateTime.now().plusMonths(tmp);
                user.setBanExpired(banDateExpired);
                updatedPoints -=tmp*10;
                user.setPenaltyPoints(updatedPoints);
                userRepository.save(user);
            }
        }
    }

    public static int penaltyPointsCalc(List<Book> rentedBooks){
        int RENTING_PERIOD = RentService.RENTING_PERIOD;
        LocalDateTime now = LocalDateTime.now();
        int points = 0;
        for (Book book:
                rentedBooks) {
            LocalDateTime rentedDate = book.getRentDate();
            Period period = Period.between(now.toLocalDate(),rentedDate.toLocalDate());
            int diff = Math.abs(period.getDays());
            if(diff>RENTING_PERIOD){
                points+=(diff-RENTING_PERIOD);
            }
        }
        return points;
    }

    public static boolean validUserId(Long userId, UserRepository userRepository){
        return userRepository.existsById(userId);
    }
}
