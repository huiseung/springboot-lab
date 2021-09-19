package com.example.multithread;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "holder")
@NoArgsConstructor
public class Holder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "holder")
    private List<Account> accountList = new ArrayList<>();

    private String name;

    public Holder(String name){
        this.name = name;
    }

}
