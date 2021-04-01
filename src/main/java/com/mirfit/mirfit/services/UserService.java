package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {

    AddUserResponse addUser(AddUserRequest request);

    GetUserResponse getUserById(UUID id);

    String deleteUserById(UUID id);

    String updateLogin(UpdateLoginRequest request);

    String updatePassword(UpdatePasswordRequest request);

    AuthUserResponse authorizeUser(AuthUserRequest request);

    List<User> getAllUsers();
}
