package com.example.demo.validator;

import com.example.demo.model.Account;
import com.example.demo.model.Currency;
import com.example.demo.model.Exchange;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;


@Component
public class ExchangeValidator {


    public boolean validate(Exchange exchange, Account account) {

        return (
            isAmount(exchange.getAmount()) &&
            areValidCurrencies(exchange.getFrom(), exchange.getTo()) &&
            areDifferCurrencies(exchange.getFrom(), exchange.getTo()) &&
            userHasMoneyToExchange(exchange, account));
    }

    //TODO: rethink amount criteria, for now less than 1.00 is not allowed (cast to int)
    private  boolean isAmount(BigDecimal amount) {
        return amount.intValue()>0;
    }


    private boolean areValidCurrencies(Currency from, Currency to) {

        Currency[] currencies = Currency.values();

        int validCurrencies = (int) Arrays.stream(currencies)
                .filter((currency) -> currency.equals(from) || currency.equals(to)).count();

        return (validCurrencies==2);
    }


    private boolean areDifferCurrencies(Currency from, Currency to) {
        return !(from.equals(to));
    }


    private boolean userHasMoneyToExchange(Exchange exchange, Account account) {

        BigDecimal moneyOnAccount = (exchange.getFrom().equals(Currency.USD)) ? account.getUsd() : account.getPln();

        return moneyOnAccount.compareTo(exchange.getAmount()) >= 0;
    }

}
