package com.SkpZSeb.biblioteka.service;

import com.SkpZSeb.biblioteka.libExceptions.UserNotFoundLibException;
import com.SkpZSeb.biblioteka.model.Book;
import com.SkpZSeb.biblioteka.model.User;
import com.SkpZSeb.biblioteka.repository.BookRepository;
import com.SkpZSeb.biblioteka.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDelete {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public String deleteById(Long id) throws UserNotFoundLibException {
       final User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundLibException("Bad id in request."));
        List<Book> listRentedBooks =
    }

}
