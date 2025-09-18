package org.maksymtiutiunnyk.somcooked.controller;

import org.maksymtiutiunnyk.somcooked.dtos.UserRegistrationDto;
import org.maksymtiutiunnyk.somcooked.dtos.UserRegistrationResponseDto;
import org.maksymtiutiunnyk.somcooked.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRegistrationService userRegistrationService;
    @Autowired
    public AuthController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/register")
    public UserRegistrationResponseDto registerUser(@RequestBody UserRegistrationDto user) {
        return userRegistrationService.register(user);
    }
}
