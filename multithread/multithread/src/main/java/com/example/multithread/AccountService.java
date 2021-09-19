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
    public Long deposit(Long accountId, Long amount){
        Account account = accountRepository.findById(accountId).orElseThrow();
        Long newBalance = account.getBalance()+amount;
        account.setBalance(newBalance);
        accountRepository.save(account);
        return newBalance;
    }
}
