package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {

    AddUserResponse addUser(AddUserRequest request);

    GetUserResponse getUserById(UUID id);

    String deleteAllUsers();

    String deleteUserById(UUID id);

    String updateUserById(UUID id);

    AuthUserResponse authorizeUser(AuthUserRequest request);
}
