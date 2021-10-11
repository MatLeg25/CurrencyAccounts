package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Exchange {

    private String userPesel;
    private Currency from;
    private Currency to;
    private BigDecimal amount;

}
