package com.example.multithread;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name="account")
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Holder holder;

    private String name;
    private Long balance;

    public Account(String name){
        this.name = name;
        this.balance = 0L;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
