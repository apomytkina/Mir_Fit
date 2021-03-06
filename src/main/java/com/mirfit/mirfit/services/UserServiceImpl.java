package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.*;
import com.mirfit.mirfit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AddUserResponse addUser(AddUserRequest request) {
        return userRepository.add(request);
    }

    @Override
    public GetUserResponse getUserById(UUID id) {
        return userRepository.getById(id);
    }

    @Override
    public String deleteAllUsers() {
        return null;
    }

    @Override
    public String deleteUserById(UUID id) {
        return userRepository.deleteUser(id);
    }

    @Override
    public String updateUserById(UUID id) {
        return null;
    }

    @Override
    public AuthUserResponse authorizeUser(AuthUserRequest request) {
        return userRepository.authUser(request);
    }
}
