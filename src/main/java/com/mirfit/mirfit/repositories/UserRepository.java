package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    GetUserResponse getById(long id);

    String add(AddUserRequest request);

    AuthUserResponse authUser(AuthUserRequest request);
}
