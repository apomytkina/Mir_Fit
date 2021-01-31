package com.mirfit.mirfit.controllers;

import com.mirfit.mirfit.models.AddUserRequest;
import com.mirfit.mirfit.models.AddUserResponse;
import com.mirfit.mirfit.models.User;
import com.mirfit.mirfit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUserById(long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public AddUserResponse addUser(AddUserRequest user) {
        String error = userService.addUser(user);
        return new AddUserResponse(error);
    }
}
