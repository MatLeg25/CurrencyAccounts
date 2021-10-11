package com.example.demo.service;

import com.example.demo.model.AppUser;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, AccountRepository accountRepository) {
        this.appUserRepository = appUserRepository;
        this.accountRepository = accountRepository;
    }

    public Optional<AppUser> getUserByPesel(String pesel) {
        return this.appUserRepository.findAppUserByPesel(pesel);

    }

    public List<AppUser> getAllUsers() {
        return this.appUserRepository.findAll();
    }

}
