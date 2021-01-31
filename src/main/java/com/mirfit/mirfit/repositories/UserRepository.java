package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.AddUserRequest;
import com.mirfit.mirfit.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User getById(long id);
    String add(AddUserRequest request);
}
