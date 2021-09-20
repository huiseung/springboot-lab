package com.example.multithread;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public Long saveAccount(Account account){
        return accountRepository.save(account).getId();
    }

    @Transactional
    public Account findAccountById(Long accountId){
        return accountRepository.findById(accountId).orElseThrow();
    }

    @Transactional
    public long deposit(Long accountId, long amount){
        Account account = accountRepository.findById(accountId).orElseThrow();
        long curBalance = account.getBalance();
        System.out.println("thread= "+Thread.currentThread().getName()+ ", " + "balance= "+curBalance);
        long newBalance = curBalance+amount;
        System.out.println("thread= "+Thread.currentThread().getName()+ ", " + "balance= "+newBalance);
        account.setBalance(newBalance);
        accountRepository.save(account);
        return newBalance;
    }
}
