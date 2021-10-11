package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Rate {

    private String no;
    private String effectiveDate;
    private BigDecimal bid;
    private BigDecimal ask;
}
