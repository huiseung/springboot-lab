package com.example.redis.domain.member;


import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@RedisHash(value = "member", timeToLive = 30)
public class Member {
    @Id
    private String id;

    private String name;
    private Integer age;
    private LocalDateTime createdTime;

    public Member(String name, Integer age){
        this.name = name;
        this.age = age;
        this.createdTime = LocalDateTime.now();
    }
}
