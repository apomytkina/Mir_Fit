package com.mirfit.mirfit.services;

import com.mirfit.mirfit.models.CardDto;
import com.mirfit.mirfit.models.GetBonusesResponse;
import com.mirfit.mirfit.models.GetCardsResponse;
import com.mirfit.mirfit.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

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
        if (id != null)
            return cardRepository.addCard(id, card);
        else
            return "Id cannot be null.";
    }
}
