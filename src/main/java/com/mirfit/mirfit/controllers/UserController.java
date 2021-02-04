package com.mirfit.mirfit.controllers;

import com.mirfit.mirfit.models.*;
import com.mirfit.mirfit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> getUserById(long id) {

        var result = userService.getUserById(id);
        if (result.getError() == null) {
            return new ResponseEntity(result.getUser(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(result.getError(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("addUser")
    public String addUser(@RequestBody AddUserRequest user) {
        return userService.addUser(user);
    }

    @PostMapping("authUser")
    public ResponseEntity<AuthUserResponse> authorizeUser(@RequestBody  AuthUserRequest request) {
        var result = userService.authorizeUser(request);

        if (result.getError() != null) {
            return new ResponseEntity(result.getError(), HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }

    @GetMapping("delete")
    public ResponseEntity<String> deleteUser(long id) {
        var result = userService.deleteUserById(id);

        if (result == null) {
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
        else {
            return  new ResponseEntity(result, HttpStatus.OK);
        }
    }
}
