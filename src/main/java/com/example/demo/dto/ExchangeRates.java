package com.example.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExchangeRates {

    private char table;
    private String currency;
    private String code;
    private List<Rate> rates = new ArrayList<>();
}
