package com.mirfit.mirfit.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirfit.mirfit.models.CardDto;
import com.mirfit.mirfit.models.GetBonusesResponse;
import com.mirfit.mirfit.models.GetCardsResponse;
import com.mirfit.mirfit.repositories.CardRepository;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class CardServiceImpl implements CardService {
    private static ObjectMapper mapper = new ObjectMapper();
    private final CardRepository cardRepository;
    private final String BASE_URL = "https://mir-acquirer.herokuapp.com/info/user-exists";
    private static final OkHttpClient client = new OkHttpClient();

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public String updateBonuses(String cardNumber, double numberOfBonuses) {
        if (cardNumber != null)
            return cardRepository.updateBonuses(cardNumber, numberOfBonuses);
        else
            return "Card number cannot be null.";
    }

    @Override
    public GetCardsResponse getCards(UUID id) {
        if (id != null)
            return cardRepository.getCardsByUserId(id);
        else
            return new GetCardsResponse("Id cannot be null.", null);
    }

    @Override
    public GetBonusesResponse getBonuses(String cardNumber) {
        if (cardNumber != null)
            return cardRepository.getBonuses(cardNumber);
        else
            return new GetBonusesResponse("Card number cannot be null.", 0);
    }

    @Override
    public String delete(UUID id) {
        if (id != null)
            return cardRepository.delete(id);
        else
            return "Id cannot be null.";
    }

    @Override
    public String delete(String cardNumber) {
        if (cardNumber != null)
            return cardRepository.delete(cardNumber);
        else
            return "Card number cannot be null.";
    }

    @Override
    public String updateName(String cardNumber, String name) {
        if (cardNumber != null)
            return cardRepository.updateName(cardNumber, name);
        else
            return "Card number cannot be null.";
    }

    @Override
    public String addCard(UUID id, CardDto card) {
        // Check if user is a client of bank
        if (!validateCardByCore(card.getNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card not found");
        }

        if (id != null)
            return cardRepository.addCard(id, card);
        else
            return "Id cannot be null.";
    }

    private boolean validateCardByCore(String cardNumber) {
        try {
            Request request = new Request.Builder()
                    .url(BASE_URL + "?cardNumber=" + cardNumber)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
               return  Boolean.parseBoolean(response.body().string());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error connecting to core");
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error connecting to core");
        }
    }
}
