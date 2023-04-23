package com.SkpZSeb.biblioteka.service;

import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.model.BookingRecord;
import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.repository.BookRepository;
import com.SkpZSeb.biblioteka.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStatusUpdate {

    private final PenaltyPointReset penaltyPointReset;
    private final RentService rentService;

    public void updateUserInfo(User user, UserService userService, BookService bookService){
            int newPoints = 0;
            List<BookingRecord> rentedBooks = rentService.allRentedBooksByUser(user);

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
                    userService.save(user);
                }

            } else penaltyPointReset.resetCheck(user, userService);
            banExpired(user,userService);
    }

    private boolean banExpired(User user, UserService userService){
        if(user.getBanExpired()!=null && user.getBanExpired().toLocalDate().isBefore(LocalDate.now())){
            user.setBanExpired(null);
            userService.save(user);
            return true;
        }else return false;
    }

    public int penaltyPointsCalc(List<BookingRecord> rentedBooks, User user){
        int points = 0;
        Period periodB;
            for (BookingRecord bookingRecord :
                    rentedBooks) {

                LocalDateTime rentedDate = bookingRecord.getRentDate();
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


}
