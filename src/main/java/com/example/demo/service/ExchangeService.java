package com.example.demo.service;

import com.example.demo.client.ExchangeClient;
import com.example.demo.model.Account;
import com.example.demo.model.AppUser;
import com.example.demo.model.Currency;
import com.example.demo.model.Exchange;
import com.example.demo.validator.ExchangeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
@Slf4j
public class ExchangeService {

    private final ExchangeClient exchangeClient;
    private final AppUserService appUserService;
    private final AccountService accountService;
    private final ExchangeValidator exchangeValidator;


    @Autowired
    public ExchangeService(ExchangeClient exchangeClient, AppUserService appUserService, AccountService accountService, ExchangeValidator exchangeValidator) {
        this.exchangeClient = exchangeClient;
        this.appUserService = appUserService;
        this.accountService = accountService;
        this.exchangeValidator = exchangeValidator;
    }

    //TODO: incorrect data validation
    @Transactional
    public ResponseEntity<String> exchange(Exchange exchange) {

        Optional<AppUser> appUserCheck = this.appUserService.getUserByPesel(exchange.getUserPesel());

        if(appUserCheck.isPresent() && exchangeValidator.validate(exchange, appUserCheck.get().getAccount())) {
            performExchange(exchange, appUserCheck.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Incorrect data - try again!");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("Exchange completed successfully!");
    }


    private void performExchange(Exchange exchange, AppUser appUser) {

        log.info("State before transaction: "+ appUser.toString());

        Currency from = exchange.getFrom();
        Currency to = exchange.getTo();
        BigDecimal amount = exchange.getAmount();
        Account account = appUser.getAccount();

        if (from.equals(Currency.PLN) && to.equals(Currency.USD)) {
            appUser.setAccount(exchangeFromPlnToUsd(amount, account));
        } else if (from.equals(Currency.USD) && to.equals(Currency.PLN)) {
            appUser.setAccount(exchangeFromUsdToPln(amount, account));
        }

        this.accountService.updateAccount(account);

        log.info("State after transaction: "+ appUser.toString());
    }


    private Account exchangeFromPlnToUsd(BigDecimal amount, Account account) {
       log.info("Exchange from PLN to USD");

        BigDecimal bidRate = this.exchangeClient.getBidRate(Currency.USD);

        BigDecimal exchangedPLN = account.getPln().subtract(amount);
        BigDecimal exchangedUSD = account.getUsd().add((amount.divide(bidRate, 2, RoundingMode.HALF_UP)));

        account.setPln(exchangedPLN);
        account.setUsd(exchangedUSD);

        return account;
    }


    private Account exchangeFromUsdToPln(BigDecimal amount, Account account) {
        log.info("Exchange from USD to PLN");

        BigDecimal askRate = this.exchangeClient.getAskRate(Currency.USD);

        BigDecimal exchangedUSD = account.getUsd().subtract(amount);
        BigDecimal exchangedPLN = account.getPln().add(amount.multiply(askRate));

        account.setPln(exchangedPLN);
        account.setUsd(exchangedUSD);

        return account;
    }


}
