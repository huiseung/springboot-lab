package com.example.swagger;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    @Email
    private String email;
}
