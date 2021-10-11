package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AppUserController {

    private final AppUserService appUserService;


    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @GetMapping("/api/v1/user/{pesel}")
    public Optional<AppUser> getUserByPesel(@PathVariable String pesel) {
        return appUserService.getUserByPesel(pesel);
    }


    @GetMapping("/api/v1/user")
    public List<AppUser> getAllUsers() {
        return appUserService.getAllUsers();
    }


    @PostMapping("/api/v1/registration")
    public ResponseEntity<String> registration(@RequestBody AppUser appUser) {
        return appUserService.registration(appUser);
    }

}
