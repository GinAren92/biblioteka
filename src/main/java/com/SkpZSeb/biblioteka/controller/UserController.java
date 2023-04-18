package com.SkpZSeb.biblioteka.controller;

import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all-users")
    public List<User> allUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/add-user")
    public User addUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
