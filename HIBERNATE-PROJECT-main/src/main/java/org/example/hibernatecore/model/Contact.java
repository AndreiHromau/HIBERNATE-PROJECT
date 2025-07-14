package org.example.hibernatecore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity //подружить java класс с sql таблицей (смаппить класс).
@Table(name = "users") //смаппить класс users в таблицу users.

public class Contact {

    @Id //смаппить поле id в колонку id и тд.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //сгенерировать авто значение и запись его в колонку
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;
}

