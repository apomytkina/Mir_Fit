package com.mirfit.mirfit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mirfit.mirfit.models.*;
import com.mirfit.mirfit.repositories.CardRepository;
import okhttp3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("/application.properties")
@ContextConfiguration
class CardControllerTest {

    @Autowired
    private CardRepository cardRepository;
    private final OkHttpClient client = new OkHttpClient();
    private final String URL = "http://localhost:8080/cards/";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final ObjectMapper mapper = new ObjectMapper();
    private final UUID testUserId = UUID.fromString("badd0647-ce24-484d-b1e9-d2517cee90c1");
    private final String testCardNumber = "testCardNumber";

    @Test
    void getBonuses() throws IOException {
        //Check if returned status code is 404
        Response response = getBonuses(testCardNumber);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.code());

        CardDto cardDto = new CardDto("testCard", testCardNumber);
        cardRepository.addCard(testUserId, cardDto);

        //Check if returned status code is 200
        response = getBonuses(testCardNumber);
        assertEquals(HttpStatus.OK.value(), response.code());

        //Check if returned status code is 400 as card number cannot be null
        response = getBonuses(null);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.code());

        cardRepository.delete(testCardNumber);
    }

    @Test
    void updateName() throws IOException {
        CardDto cardDto = new CardDto("testCardName", testCardNumber);
        cardRepository.addCard(testUserId, cardDto);

        UpdateNameRequest request = new UpdateNameRequest("newTestCardName", testCardNumber);

        //Check if returned status code is 200
        Response response = updateName(request);
        assertEquals(HttpStatus.OK.value(), response.code());

        request = new UpdateNameRequest(null, null);

        //Check if returned status code is 400 as card number cannot be null
        response = updateName(request);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.code());

        cardRepository.delete(testCardNumber);

        request = new UpdateNameRequest("newTestCardName", testCardNumber);

        //Check if returned status code is 404
        response = updateName(request);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.code());
    }

    @Test
    void addCard() throws IOException {
        AddCardRequest request = new AddCardRequest(testUserId, new CardDto("testCardName", testCardNumber));

        //Check if returned status code is 200
        Response response = addCard(request);
        assertEquals(HttpStatus.OK.value(), response.code());

        //Check if returned status code is 400
        response = addCard(request);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.code());

        cardRepository.delete(testCardNumber);
    }

    @Test
    void getCardsByUserId() throws IOException {
        Response response = getCardsByUserId(testUserId);

        Type listType = new TypeToken<ArrayList<Card>>() {
        }.getType();
        Gson gson = new Gson();
        List<Card> cards = gson.fromJson(Objects.requireNonNull(response.body()).string(), listType);

        //Check if number of cards is 0
        Assertions.assertEquals(0, cards.size());

        cardRepository.addCard(testUserId, new CardDto("testCardName", testCardNumber));

        //Check if returned status code is 200
        response = getCardsByUserId(testUserId);
        assertEquals(HttpStatus.OK.value(), response.code());

        gson = new Gson();
        cards = gson.fromJson(Objects.requireNonNull(response.body()).string(), listType);

        //Check if number of cards is 1
        Assertions.assertEquals(1, cards.size());

        cardRepository.delete(testCardNumber);
    }

    @Test
    void increase() throws IOException {
        cardRepository.addCard(testUserId, new CardDto("testCardName", testCardNumber));

        UpdateBonusesRequest updateBonusesRequest = new UpdateBonusesRequest(testCardNumber, 100);

        //Check if returned status code is 200
        Response response = increase(updateBonusesRequest);
        Assertions.assertEquals(HttpStatus.OK.value(), response.code());

        response = getBonuses(testCardNumber);

        Gson gson = new Gson();
        Double bonuses = gson.fromJson(Objects.requireNonNull(response.body()).string(), Double.class);

        //Check if number of bonuses is 100
        Assertions.assertEquals(100, bonuses);

        cardRepository.delete(testCardNumber);

        //Check if returned status code is 404
        response = increase(updateBonusesRequest);
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.code());
    }

    @Test
    void decrease() throws IOException {
        cardRepository.addCard(testUserId, new CardDto("testCardName", testCardNumber));

        UpdateBonusesRequest updateBonusesRequest = new UpdateBonusesRequest(testCardNumber, 100);

        //Check if returned status code is 400 as number of bonuses cannot be negative
        Response response = decrease(updateBonusesRequest);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.code());

        //Add 100 bonuses
        increase(updateBonusesRequest);

        //Check if returned status code is 200
        response = decrease(updateBonusesRequest);
        Assertions.assertEquals(HttpStatus.OK.value(), response.code());

        response = getBonuses(testCardNumber);

        Gson gson = new Gson();
        Double bonuses = gson.fromJson(Objects.requireNonNull(response.body()).string(), Double.class);

        //Check if number of bonuses is 0
        Assertions.assertEquals(0, bonuses);

        cardRepository.delete(testCardNumber);

        //Check if returned status code is 404
        response = decrease(updateBonusesRequest);
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.code());
    }

    @Test
    void delete() throws IOException {
        //Check if returned status code is 404
        Response response = delete(testCardNumber);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.code());

        CardDto cardDto = new CardDto("testCard", testCardNumber);
        cardRepository.addCard(testUserId, cardDto);

        //Check if returned status code is 200
        response = delete(testCardNumber);
        assertEquals(HttpStatus.OK.value(), response.code());
    }

    @Test
    void deleteAll() throws IOException {
        //Check if returned status code is 200
        Response response = deleteAll(testUserId);
        assertEquals(HttpStatus.OK.value(), response.code());
    }

    private Response getCardsByUserId(UUID userId) throws IOException {
        Request request = new Request.Builder()
                .url(URL + userId)
                .get()
                .build();

        return client.newCall(request).execute();
    }

    private Response deleteAll(UUID userId) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "all/" + userId)
                .delete()
                .build();

        return client.newCall(request).execute();
    }

    private Response delete(String cardNumber) throws IOException {
        Request request = new Request.Builder()
                .url(URL + cardNumber)
                .delete()
                .build();

        return client.newCall(request).execute();
    }

    private Response decrease(UpdateBonusesRequest updateBonusesRequest) throws IOException {
        String json = mapper.writeValueAsString(updateBonusesRequest);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(URL + "bonuses/decrease")
                .put(body)
                .build();

        return client.newCall(request).execute();
    }

    private Response increase(UpdateBonusesRequest updateBonusesRequest) throws IOException {
        String json = mapper.writeValueAsString(updateBonusesRequest);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(URL + "bonuses/increase")
                .put(body)
                .build();

        return client.newCall(request).execute();
    }

    private Response addCard(AddCardRequest addCardRequest) throws IOException {
        String json = mapper.writeValueAsString(addCardRequest);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(URL + "/add")
                .post(body)
                .build();

        return client.newCall(request).execute();
    }

    private Response getBonuses(String cardNumber) throws IOException {
        Request request;

        if (cardNumber == null) {
            request = new Request.Builder()
                    .url(URL + "bonuses/")
                    .get()
                    .build();
        } else {
            request = new Request.Builder()
                    .url(URL + "bonuses/" + cardNumber)
                    .get()
                    .build();
        }

        return client.newCall(request).execute();
    }

    private Response updateName(UpdateNameRequest updateNameRequest) throws IOException {
        String json = mapper.writeValueAsString(updateNameRequest);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(URL)
                .patch(body)
                .build();

        return client.newCall(request).execute();
    }
}