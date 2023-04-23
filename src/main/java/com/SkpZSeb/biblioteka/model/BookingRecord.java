package com.SkpZSeb.biblioteka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "booking")
public class BookingRecord {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "booking_id", nullable = false)
        private Long id;


        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "user_id")
        private User user;

        @NotNull
        @Column(name = "book_id", nullable = false)
        private Long bookId;

        @NotNull
        @Column(name = "title", nullable = false)
        private String title;

        @NotNull
        @Column(name = "rent_date")
        private LocalDateTime rentDate;

        @NotNull
        @Column(name = "return_date")
        private LocalDateTime returnDate;




}
