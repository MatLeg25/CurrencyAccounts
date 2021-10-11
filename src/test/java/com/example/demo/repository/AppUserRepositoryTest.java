package com.example.demo.repository;

import com.example.demo.model.AppUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepositoryTest;

    @AfterEach
    void tearDown() {
        appUserRepositoryTest.deleteAll();
    }

    @Test
    void itShouldCheckIfUserExists() {
        AppUser appUser = new AppUser();
        appUser.setName("Name");
        appUser.setPesel("99111178901");
        appUserRepositoryTest.save(appUser);

        boolean expected = appUserRepositoryTest.existsAppUserByPesel(appUser.getPesel());

        assertTrue(expected);
    }


    @Test
    void itShouldCheckIfUserNotExists() {
        AppUser appUser = new AppUser();
        appUser.setName("Name");
        appUser.setPesel("99111178901");

        boolean expected = appUserRepositoryTest.existsAppUserByPesel(appUser.getPesel());

        assertFalse(expected);
    }
}