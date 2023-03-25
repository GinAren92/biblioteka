package com.SkpZSeb.biblioteka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.LONG32VARCHAR)
    private Long id;

    @Column(name = "title", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String title;

    @Column(name = "rent_date")
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private String rentDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User user;

}
