package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.validator.PeselValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

class AppUserServiceTest {

    @Mock private AppUserRepository appUserRepository;
    @Mock private AccountRepository accountRepository;
    @Mock private PeselValidator peselValidator;

    private AutoCloseable autoCloseable;
    private AppUserService appUserServiceTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        appUserServiceTest = new AppUserService(appUserRepository, accountRepository, peselValidator);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }


    @Test
    void canGetUserByPesel() {
        String PESEL = "99111178901";
        AppUser appUser = new AppUser();
        appUser.setName("Name");
        appUser.setSurname("Surname");
        appUser.setPesel(PESEL);

        appUserServiceTest.getUserByPesel(PESEL);

        verify(appUserRepository).findAppUserByPesel(PESEL);
    }


    @Test
    void canGetAllUsers() {
        appUserServiceTest.getAllUsers();
        verify(appUserRepository).findAll();
    }

    
}