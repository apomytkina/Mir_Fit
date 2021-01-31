package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.AddUserRequest;
import com.mirfit.mirfit.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String addUser(AddUserRequest request);
    User getUserById(long id);
}
