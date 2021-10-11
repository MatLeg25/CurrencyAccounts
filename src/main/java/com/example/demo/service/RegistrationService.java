package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.validator.PeselValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
@Slf4j
public class RegistrationService {

    private final AppUserRepository appUserRepository;
    private final AccountRepository accountRepository;
    private final PeselValidator peselValidator;


    @Autowired
    public RegistrationService(AppUserRepository appUserRepository, AccountRepository accountRepository, PeselValidator peselValidator) {
        this.appUserRepository = appUserRepository;
        this.accountRepository = accountRepository;
        this.peselValidator = peselValidator;
    }


    @Transactional
    public ResponseEntity<String> registration(AppUser appUser) {

        if(isNewUser(appUser.getPesel()) && peselValidator.validate(appUser.getPesel())) {

            Account account = appUser.getAccount();
            account.setPln((account.getPln() != null ? account.getPln() : new BigDecimal(0)));
            account.setUsd(new BigDecimal(0));
            account.setUserPesel(appUser.getPesel());

            this.accountRepository.save(account);

            AppUser user = this.appUserRepository.saveAndFlush(appUser);

            log.info("New user added: " + user.toString());

            return ResponseEntity.status(HttpStatus.OK)
                    .body("New user added: " + appUser.getName() + " , PESEL="+appUser.getPesel());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The user with given PESEL("+appUser.getPesel()+") cannot be added to DB or already exists!");
        }
    }


    private boolean isNewUser(String pesel) {
        return !this.appUserRepository.existsAppUserByPesel(pesel);
    }

}
