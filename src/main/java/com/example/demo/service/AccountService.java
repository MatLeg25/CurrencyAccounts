package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void updateAccount(Account account) {
        Account updatedAccount = this.accountRepository.getById(account.getId());

        updatedAccount.setPln(account.getPln());
        updatedAccount.setUsd(account.getUsd());

        this.accountRepository.save(updatedAccount);

    }
}
