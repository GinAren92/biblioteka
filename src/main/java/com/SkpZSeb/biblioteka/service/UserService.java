package com.SkpZSeb.biblioteka.service;

import com.SkpZSeb.biblioteka.libExceptions.UserNotFoundLibException;
import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundLibException("User ID not found"));
        return user;
    }

    public boolean validUserId(Long userId){
        return userRepository.existsById(userId);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public String deleteUser(Long userId){
        try {
            userRepository.deleteById(userId);
            return "User with ID: "+userId+" deleted with all records.";
        }catch (Exception e){
            log.error("error",e);
            return "Something go wrong: "+e.getStackTrace().toString();
        }
    }
}
