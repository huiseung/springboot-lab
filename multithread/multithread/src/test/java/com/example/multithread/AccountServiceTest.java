package com.example.multithread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class AccountServiceTest {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(100);
    
    @Autowired
    private AccountService accountService;

    private Long accountId;

    @BeforeEach
    public void setup(){
        Account account = new Account("신한 S20");
        accountId = accountService.saveAccount(account);
    }
    @Test
    public void DepositWithRaceCondition() throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(100);
        for(int i = 0; i < 100; i++){

        }


    }
}
