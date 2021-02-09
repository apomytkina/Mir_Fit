package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.AddUserRequest;
import com.mirfit.mirfit.repositories.UserRepository;
import com.mirfit.mirfit.repositories.UserRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application.properties")
class UserServiceImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;


    @Autowired
    private UserService userService;

    AddUserRequest addUserRequest;

    @BeforeEach
    void setUp() {
        Assumptions.assumeTrue(true);
    }

    @AfterEach
    void deleteTestInfo() {
        deleteTestRow();
    }

    @Test
    void addUser() {
        addUserRequest = new AddUserRequest("Иванов", "Иван", "Иванович",
                "123456789876543", "givememoney", "el-desu");

       //  var result = userService.addUser(addUserRequest);
    }

    @Test
    void getUserById() {
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void authorizeUser() {
    }


    private void deleteTestRow() {
        userRepository.getJdbcTemplate().update(
                "DELETE FROM user ORDER BY id DESC LIMIT 1");
    }
}