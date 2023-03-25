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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    private String lastName;

    @Column(name = "penalty_points")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private String penaltyPoints;

    @Column(name = "date_last_points")
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private String dateLastPoints;

    @Column(name = "ban_expired")
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private String banExpired;

    public String getFirstName() {
        return firstName;
    }

}
