package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity
@Data
@NoArgsConstructor
@Component
public class Account {

    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;

    @JsonIgnore
    private String userPesel;

   @JsonProperty("PLN")
    private BigDecimal pln;

    @JsonProperty("USD")
    private BigDecimal usd;

}
