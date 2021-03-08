package com.mirfit.mirfit.repositories;

import com.mirfit.mirfit.models.CardDto;
import com.mirfit.mirfit.models.GetBonusesResponse;
import com.mirfit.mirfit.models.GetCardsResponse;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardRepository {
    String updateBonuses(String cardNumber, double numberOfBonuses);

    GetCardsResponse getCardsByUserId(UUID userId);

    GetBonusesResponse getBonuses(String cardNumber);

    String addCard(UUID userId, CardDto card);

    String delete(UUID userId);

    String delete(String cardNumber);

    String updateName(String cardNumber, String name);
}
