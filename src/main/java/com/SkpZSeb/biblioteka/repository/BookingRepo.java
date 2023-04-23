package com.SkpZSeb.biblioteka.repository;

import com.SkpZSeb.biblioteka.model.BookingRecord;
import com.SkpZSeb.biblioteka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<BookingRecord, Long> {
    BookingRecord findByUserAndBookId(User user, Long bookId);
    boolean existByUserAndBookId(User user, Long bookId);

    List<BookingRecord> findByUser(User user);
}
