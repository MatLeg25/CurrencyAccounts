package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import com.example.demo.model.Account;

import javax.persistence.*;

@Data
@Entity
@JsonPropertyOrder({"name","surname","pesel","account"})
public class AppUser {

    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;

    @JsonProperty("PESEL")
    private String pesel;
    private String name;
    private String surname;

    @OneToOne
    private Account account;



}
