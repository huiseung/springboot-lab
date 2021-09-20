package com.example.multithread;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

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
        //given
        int numThread = 100;
        long money = 10;
        CountDownLatch latch = new CountDownLatch(numThread);
        //when
        for(int i = 0; i < 100; i++){
            executorService.execute(()->{
                accountService.deposit(accountId, money);
                latch.countDown();
            });
        }
        latch.await();
        Account findAccount = accountService.findAccountById(accountId);
        //then
        assertThat(findAccount.getBalance()).isEqualTo(numThread*money);
    }
}
