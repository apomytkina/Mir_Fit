package com.mirfit.mirfit.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirfit.mirfit.models.AddUserRequest;
import com.mirfit.mirfit.models.AuthUserRequest;
import com.mirfit.mirfit.models.User;
import com.mirfit.mirfit.repositories.UserRepositoryImpl;
import com.mirfit.mirfit.rowmappers.UserRowMapper;
import okhttp3.*;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;


import java.io.IOException;
import java.util.UUID;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("/application.properties")
@ContextConfiguration
class gitUserControllerTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    AddUserRequest addUserRequest;
    OkHttpClient client;

    private final String BASE_URL = "http://localhost:8080/users/";
    private static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private ObjectMapper mapper;


    @BeforeEach
    void setUp() {
        client = new OkHttpClient();
        Assumptions.assumeTrue(true);
        mapper = new ObjectMapper();
    }

    @Test
    void addUser() throws IOException {

        addUserRequest = new AddUserRequest("Иванов", "Иван", "Иванович",
                "123456789876543", "givememoney", "coollogin");

        String json = mapper.writeValueAsString(addUserRequest);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(BASE_URL + "addUser")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        Assertions.assertNotNull(response);

        // Сhecking status code
        Assertions.assertEquals(HttpStatus.OK.value(), response.code());

        response = client.newCall(request).execute();
        Assertions.assertEquals(HttpStatus.I_AM_A_TEAPOT.value(), response.code());

        // Check that user added and data correct
        User addedUser = userRepository.getJdbcTemplate()
                .query("SELECT * FROM user ORDER BY id DESC LIMIT 1", new UserRowMapper())
                .get(0);

        Assertions.assertEquals(addedUser.getLogin(), addUserRequest.getLogin());
        Assertions.assertEquals(addedUser.getFirstName(), addUserRequest.getFirstName());
        Assertions.assertEquals(addedUser.getSecondName(), addUserRequest.getSecondName());
        Assertions.assertEquals(addedUser.getCardNumber(), addUserRequest.getCardNumber());
        Assertions.assertEquals(addedUser.getPassword(), addUserRequest.getPassword());

        deleteTestRow();
    }

    @Test
    void getUserById() throws IOException {

        User lastUser = userRepository.getJdbcTemplate()
                .query("SELECT * FROM user ORDER BY id DESC LIMIT 1", new UserRowMapper())
                .get(0);

        Request request = new Request.Builder()
                .url(BASE_URL + "?id=" + lastUser.getId())
                .build();

        Response response = client.newCall(request).execute();

        Assertions.assertEquals(HttpStatus.OK.value(), response.code());

        request = new Request.Builder()
                .url(BASE_URL + "?id=" + lastUser.getId() + 1)
                .build();

        response = client.newCall(request).execute();
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.code());
    }

    @Test
    void deleteUserById() throws IOException {
        userRepository.add(new AddUserRequest("Иванов", "Иван", "Иванович",
                "123456789876543", "givememoney", "coollogin"));

        long countUsers =  userRepository.getJdbcTemplate()
                .query("SELECT * FROM user", new UserRowMapper())
                .size();

        UUID lastId = userRepository.getJdbcTemplate()
                .query("SELECT * FROM user ORDER BY id DESC LIMIT 1", new UserRowMapper())
                .get(0)
                .getId();

        Request request = new Request.Builder()
                .url(BASE_URL + "delete?id=" + lastId)
                .build();

        Response response = client.newCall(request).execute();
        Assertions.assertEquals(HttpStatus.OK.value(), response.code());

        long newSize = userRepository.getJdbcTemplate()
                .query("SELECT * FROM user ", new UserRowMapper())
                .size();

        Assertions.assertEquals(countUsers, newSize + 1);
    }

    @Test
    void authorizeUser() throws IOException {

        userRepository.add(new AddUserRequest("Иванов", "Иван", "Иванович",
                "123456789876543", "givememoney", "coollogin"));

        AuthUserRequest addRequest = new AuthUserRequest("coollogin", "givememoney");
        String json = mapper.writeValueAsString(addRequest);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(BASE_URL + "authUser")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK.value(), response.code());

        addRequest = new AuthUserRequest("coollogin", "12345");
        json = mapper.writeValueAsString(addRequest);
        body = RequestBody.create(JSON, json);
        request = new Request.Builder()
                .url(BASE_URL + "authUser")
                .post(body)
                .build();

        response = client.newCall(request).execute();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.I_AM_A_TEAPOT.value(), response.code());

        deleteTestRow();
    }

    private void deleteTestRow() {
        userRepository.getJdbcTemplate().update(
                "DELETE FROM user ORDER BY id DESC LIMIT 1");
    }
}