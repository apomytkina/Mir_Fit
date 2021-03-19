package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.*;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository {

    GetUserResponse getById(UUID id);

    AddUserResponse add(AddUserRequest request);

    AuthUserResponse authUser(AuthUserRequest request);

    String deleteUser(UUID id);

    String updateLogin(UpdateLoginRequest request);

    String updatePassword(UpdatePasswordRequest request);
}
