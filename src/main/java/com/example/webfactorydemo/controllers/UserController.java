package com.example.webfactorydemo.controllers;

import com.example.webfactorydemo.models.GetUser;
import com.example.webfactorydemo.models.LoginUser;
import com.example.webfactorydemo.models.User;
import com.example.webfactorydemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public GetUser registerUser(@RequestBody @Valid User user) {
        return userService.registerUser(user);
    }

    @PostMapping(value = "/login")
    public GetUser loginUser(@RequestBody @Valid LoginUser user) throws Exception {
        return userService.loginUser(user);
    }
}




