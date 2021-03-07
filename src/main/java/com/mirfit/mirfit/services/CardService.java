package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.CardDto;
import com.mirfit.mirfit.models.GetCardsResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CardService {
    String updateBonuses(UUID id, double numberOfBonuses);

    GetCardsResponse getBonuses(UUID id);

    String delete(UUID id);

    String delete(String cardNumber);

    String updateName(String cardNumber, String name);

    String addCard(UUID id, CardDto card);
}
