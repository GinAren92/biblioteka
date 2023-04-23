package com.SkpZSeb.biblioteka.service;

import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDelete {
    public String deleteById(User user, UserRepository userRepository){
        userRepository.delete(user);
        return "";
    }

}
