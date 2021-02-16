package com.mirfit.mirfit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mirfit.mirfit.models.Bonuses;
import com.mirfit.mirfit.repositories.BonusesRepository;
import okhttp3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("/application.properties")
@ContextConfiguration
class BonusesControllerTest {

    @Autowired
    private BonusesRepository bonusesRepository;
    private final OkHttpClient client = new OkHttpClient();
    private final String URL = "http://localhost:8080/bonuses/";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void increase() throws IOException {
        bonusesRepository.add();
        long lastAddedId = bonusesRepository.getLastInsertId();

        Bonuses updateBonusesRequest = new Bonuses(lastAddedId, 100);
        String json = mapper.writeValueAsString(updateBonusesRequest);
        RequestBody body = RequestBody.create(JSON, json);

        //Check if returned status code is 200
        Request request = new Request.Builder()
                .url(URL + "increase")
                .put(body)
                .build();

        Response response = client.newCall(request).execute();
        Assertions.assertEquals(HttpStatus.OK.value(), response.code());

        //Check if number of bonuses is 100
        request = new Request.Builder()
                .url(URL + lastAddedId)
                .get()
                .build();

        response = client.newCall(request).execute();

        Gson gson = new Gson();
        Bonuses bonuses = gson.fromJson(Objects.requireNonNull(response.body()).string(), Bonuses.class);
        Assertions.assertEquals(100, bonuses.getNumberOfBonuses());

        //Check if returned status code is 404 when bonuses account does not exist
        updateBonusesRequest = new Bonuses(lastAddedId + 1, 100);
        json = mapper.writeValueAsString(updateBonusesRequest);
        body = RequestBody.create(JSON, json);

        request = new Request.Builder()
                .url(URL + "increase")
                .put(body)
                .build();

        response = client.newCall(request).execute();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.code());

        //Delete added bonuses account
        bonusesRepository.delete(lastAddedId);
    }

    @Test
    void decrease() throws IOException {
        bonusesRepository.add();
        long lastAddedId = bonusesRepository.getLastInsertId();

        Bonuses updateBonusesRequest = new Bonuses(lastAddedId, 100);
        String json = mapper.writeValueAsString(updateBonusesRequest);
        RequestBody body = RequestBody.create(JSON, json);

        //Added account has 0 bonuses, so return status code must be 400
        Request request = new Request.Builder()
                .url(URL + "decrease")
                .put(body)
                .build();

        Response response = client.newCall(request).execute();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.code());

        //Increase number of bonuses
        request = new Request.Builder()
                .url(URL + "increase")
                .put(body)
                .build();

        response = client.newCall(request).execute();
        Assertions.assertEquals(HttpStatus.OK.value(), response.code());

        //Check if number of bonuses is 100
        request = new Request.Builder()
                .url(URL + lastAddedId)
                .get()
                .build();

        response = client.newCall(request).execute();

        Gson gson = new Gson();
        Bonuses bonuses = gson.fromJson(Objects.requireNonNull(response.body()).string(), Bonuses.class);
        Assertions.assertEquals(100, bonuses.getNumberOfBonuses());

        //Check if returned status code is 404 when bonuses account does not exist
        updateBonusesRequest = new Bonuses(lastAddedId + 1, 100);
        json = mapper.writeValueAsString(updateBonusesRequest);
        body = RequestBody.create(JSON, json);

        request = new Request.Builder()
                .url(URL + "decrease")
                .put(body)
                .build();

        response = client.newCall(request).execute();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.code());
    }

    @Test
    void getBonuses() throws IOException {
        //Create new bonuses account
        bonusesRepository.add();
        long lastAddedId = bonusesRepository.getLastInsertId();

        Request request = new Request.Builder()
                .url(URL + lastAddedId)
                .get()
                .build();

        //Check if returned status code is 200
        Response response = client.newCall(request).execute();
        Assertions.assertEquals(HttpStatus.OK.value(), response.code());

        //Check if balance is 0
        Gson gson = new Gson();
        Bonuses bonuses = gson.fromJson(Objects.requireNonNull(response.body()).string(), Bonuses.class);
        Assertions.assertEquals(0, bonuses.getNumberOfBonuses());

        //Check if returned status code is 404 when bonuses account does not exist
        request = new Request.Builder()
                .url(URL + ++lastAddedId)
                .get()
                .build();

        response = client.newCall(request).execute();
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.code());

        //Check if returned status code is 400 when negative id is passed
        int wrongId = -4;
        request = new Request.Builder()
                .url(URL + wrongId)
                .get()
                .build();

        response = client.newCall(request).execute();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.code());

        //Delete added bonuses account
        bonusesRepository.delete(--lastAddedId);
    }

    @Test
    void delete() throws IOException {
        bonusesRepository.add();
        long lastAddedId = bonusesRepository.getLastInsertId();

        //Check if new bonuses account was successfully added
        Request request = new Request.Builder()
                .url(URL + lastAddedId)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        Assertions.assertEquals(HttpStatus.OK.value(), response.code());

        //Check if returned status code is 200
        request = new Request.Builder()
                .url(URL + lastAddedId)
                .delete()
                .build();

        response = client.newCall(request).execute();
        Assertions.assertEquals(HttpStatus.OK.value(), response.code());

        //Check if bonuses account was deleted (GET /bonuses/{id} should return 404)
        request = new Request.Builder()
                .url(URL + lastAddedId)
                .get()
                .build();

        response = client.newCall(request).execute();
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.code());

        //Check if returned status code is 404 when bonuses account does not exist
        request = new Request.Builder()
                .url(URL + lastAddedId)
                .delete()
                .build();

        response = client.newCall(request).execute();
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.code());

        //Check if returned status code is 400 when negative id is passed
        int wrongId = -4;
        request = new Request.Builder()
                .url(URL + wrongId)
                .delete()
                .build();

        response = client.newCall(request).execute();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.code());
    }
}