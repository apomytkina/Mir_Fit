package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.*;
import com.mirfit.mirfit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String addUser(AddUserRequest request) {
        return userRepository.add(request);
    }

    @Override
    public GetUserResponse getUserById(long id) {
       return userRepository.getById(id);
    }

    @Override
    public String deleteAllUsers() {
        return null;
    }

    @Override
    public String deleteUserById(long id) {
        return null;
    }

    @Override
    public String updateUserById(long id) {
        return null;
    }

    @Override
    public AuthUserResponse authorizeUser(AuthUserRequest request) {

        return userRepository.authUser(request);
    }
}
