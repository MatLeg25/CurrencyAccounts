package com.example.demo.controller;

import com.example.demo.model.Exchange;
import com.example.demo.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

    private final ExchangeService exchangeService;


    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }


    @PostMapping("api/v1/exchange")
    public ResponseEntity<String> exchange(@RequestBody Exchange exchange) {
        return this.exchangeService.exchange(exchange);
    }

}
