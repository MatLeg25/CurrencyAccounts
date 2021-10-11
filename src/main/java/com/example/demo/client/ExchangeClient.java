package com.example.demo.client;

import com.example.demo.dto.ExchangeRates;
import com.example.demo.model.Currency;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
//TODO by interface
public class ExchangeClient {


    //https://api.nbp.pl/api/exchangerates/rates/C/USD/?format=json
    private final String apiBasicUrl = "https://api.nbp.pl/api/exchangerates/rates/C/";


    private ExchangeRates getRatesByCurrencies(Currency currency) {
        RestTemplate restTemplate = new RestTemplate();
        String apiURL = apiBasicUrl + currency +"/?format=json";
        return restTemplate.getForObject(apiURL, ExchangeRates.class);
    }


    public BigDecimal getAskRate(Currency currency) {
        return this.getRatesByCurrencies(currency).getRates().get(0).getAsk();
    }


    public BigDecimal getBidRate(Currency currency) {
        return this.getRatesByCurrencies(currency).getRates().get(0).getBid();
    }





}
