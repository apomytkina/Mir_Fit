package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.AddUserRequest;
import com.mirfit.mirfit.models.User;
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
    public User getUserById(long id) {
        return userRepository.getById(id);
    }
}
