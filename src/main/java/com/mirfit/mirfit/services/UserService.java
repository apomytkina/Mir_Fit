package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String addUser(AddUserRequest request);

    GetUserResponse getUserById(long id);

    String deleteAllUsers();

    String deleteUserById(long id);

    String updateUserById(long id);

    AuthUserResponse authorizeUser(AuthUserRequest request);
}
