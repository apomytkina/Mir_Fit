package com.mirfit.mirfit.controllers;

import com.mirfit.mirfit.models.AddUserRequest;
import com.mirfit.mirfit.models.AuthUserRequest;
import com.mirfit.mirfit.models.User;
import com.mirfit.mirfit.services.BonusesService;
import com.mirfit.mirfit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final BonusesService bonusesService;

    @Autowired
    public UserController(UserService userService, BonusesService bonusesService) {
        this.userService = userService;
        this.bonusesService = bonusesService;
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {

        var result = userService.getUserById(id);

        if (result.getError() == null) {
            return new ResponseEntity<>(result.getUser(), HttpStatus.OK);
        } else if (result.getError().equals("User not found")) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    result.getError());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    result.getError());
        }
    }

    @PostMapping(value = "addUser", produces = "application/json")
    public ResponseEntity<UUID> addUser(@RequestBody AddUserRequest user) {

        var result = userService.addUser(user);

        if (result.getError() == null) {
            bonusesService.add(result.getId());
            return new ResponseEntity<>(result.getId(), HttpStatus.OK);
        } else if (result.getError().equals("Login is not available")) {
            throw new ResponseStatusException(
                    HttpStatus.I_AM_A_TEAPOT,
                    result.getError());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    result.getError());
        }
    }

    @PostMapping(value = "authUser", produces = "application/json")
    public ResponseEntity<User> authorizeUser(@RequestBody AuthUserRequest request) {
        var result = userService.authorizeUser(request);

        if (result.getError() == null) {
            return new ResponseEntity<>(result.getUser(), HttpStatus.OK);
        } else if (result.getError().equals("Wrong login or password")) {
            throw new ResponseStatusException(
                    HttpStatus.I_AM_A_TEAPOT,
                    result.getError()
            );
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    result.getError());
        }
    }

    @GetMapping(value = "delete/{id}", produces = "application/json")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        bonusesService.delete(id);
        var result = userService.deleteUserById(id);

        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    result);
        }
    }
}
