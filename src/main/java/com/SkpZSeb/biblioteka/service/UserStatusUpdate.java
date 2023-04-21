package com.SkpZSeb.biblioteka.service;

import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.repository.BookRepository;
import com.SkpZSeb.biblioteka.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public class UserStatusUpdate {

    public static void updateUserInfo(User user, UserRepository userRepository, BookRepository bookRepository){
            int newPoints = 0;
            List<Book> rentedBooks = RentService.allRentedBooksByUser(user.getId(), bookRepository);

                newPoints = penaltyPointsCalc(rentedBooks, user);

            if (newPoints > 0) {
                int actualPoints;
                user.setDateLastPoints(LocalDateTime.now());

                if (user.getPenaltyPoints() == null) actualPoints = 0;
                else actualPoints = user.getPenaltyPoints();

                int updatedPoints = newPoints + actualPoints;

                if (updatedPoints > 10) {
                    int tmp = updatedPoints / 10;
                    LocalDateTime banDateExpired = LocalDateTime.now().plusMonths(tmp);
                    user.setBanExpired(banDateExpired);
                    updatedPoints -= tmp * 10;
                    user.setPenaltyPoints(updatedPoints);
                    userRepository.save(user);
                }

            } else PenaltyPointReset.resetCheck(user, userRepository);
    }


    public static int penaltyPointsCalc(List<Book> rentedBooks, User user){
        int points = 0;
        Period periodB;
            for (Book book :
                    rentedBooks) {

                LocalDateTime rentedDate = book.getRentDate();
                Period periodA = Period.between(LocalDateTime.now().toLocalDate(), rentedDate.toLocalDate());
                if(user.getDateLastPoints() != null) {
                    periodB = Period.between(user.getDateLastPoints().toLocalDate(), rentedDate.toLocalDate());
                }else periodB = Period.ZERO;
                int diffA = Math.abs(periodA.getDays());
                int diffB = Math.abs(periodB.getDays());

                if (diffB < RentService.RENTING_PERIOD && diffA > RentService.RENTING_PERIOD) {

                    points += (diffA - RentService.RENTING_PERIOD);

                } else if (diffB > RentService.RENTING_PERIOD) {

                    int diffC = Math.abs(Period.between(user.getDateLastPoints().toLocalDate(), LocalDate.now()).getDays());
                    points += diffC;
                }
            }

        return points;
    }

    public static boolean validUserId(Long userId, UserRepository userRepository){
        return userRepository.existsById(userId);
    }
}
