package com.mirfit.mirfit.controllers;

import com.mirfit.mirfit.models.*;
import com.mirfit.mirfit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<User> getUserById(long id) {

        var result = userService.getUserById(id);
        if (result.getError() == null) {
            return new ResponseEntity<>(result.getUser(), HttpStatus.OK);
        }
        else if (result.getError().equals("User not found")){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    result.getError());
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    result.getError());
        }
    }

    @PostMapping(value = "addUser", produces = "application/json")
    public ResponseEntity<String> addUser(@RequestBody AddUserRequest user) {

        var result = userService.addUser(user);

        if (result == null)
        {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        else if (result.equals("Login is not available")){
            throw new ResponseStatusException(
                    HttpStatus.I_AM_A_TEAPOT,
                    result);
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    result);
        }
    }

    @PostMapping(value = "authUser", produces = "application/json")
    public ResponseEntity<User> authorizeUser(@RequestBody  AuthUserRequest request) {
        var result = userService.authorizeUser(request);

        if (result.getError() == null) {
            return new ResponseEntity<>(result.getUser(), HttpStatus.OK);
        }
        else if (result.getError().equals("Wrong login or password")) {
            throw new ResponseStatusException(
                    HttpStatus.I_AM_A_TEAPOT,
                    result.getError()
            );
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    result.getError());
        }
    }

    @GetMapping(value = "delete", produces = "application/json")
    public ResponseEntity<String> deleteUser(long id) {
        var result = userService.deleteUserById(id);

        if (result == null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    result);
        }
    }
}
