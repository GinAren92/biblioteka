package com.SkpZSeb.biblioteka.service;

import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.repository.UserRepository;

import java.time.LocalDateTime;


public class PenaltyPointReset {

    private static boolean check(User user){
        LocalDateTime now = LocalDateTime.now();
        if(user.getDateLastPoints() != null) {
            LocalDateTime resetDay = user.getDateLastPoints().plusMonths(1);
            if (now.isAfter(resetDay)) return true;
            else return false;
        }else return false;
    }

    public static boolean resetCheck(User user, UserRepository userRepository){
        if(check(user)){
            user.setPenaltyPoints(0);
            user.setDateLastPoints(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
