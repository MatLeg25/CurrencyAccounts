package com.example.demo.service;

import com.example.demo.model.AppUser;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.AppUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

class AppUserServiceTest {

    @Mock private AppUserRepository appUserRepository;
    @Mock private AccountRepository accountRepository;

    private AutoCloseable autoCloseable;
    private AppUserService appUserServiceTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        appUserServiceTest = new AppUserService(appUserRepository, accountRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetUserByPesel() {
        AppUser appUser = new AppUser();
        appUser.setName("Name");
        appUser.setPesel("99111178901");
    }

    @Test
    void canGetAllUsers() {
        appUserServiceTest.getAllUsers();
        verify(appUserRepository).findAll();
    }
}