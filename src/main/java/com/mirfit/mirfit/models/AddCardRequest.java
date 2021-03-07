package com.mirfit.mirfit.models;

import java.util.UUID;

public class AddCardRequest {
    private final UUID userId;
    private final CardDto card;

    public AddCardRequest(UUID userId, CardDto card) {
        this.userId = userId;
        this.card = card;
    }

    public CardDto getCard() {
        return card;
    }

    public UUID getUserId() {
        return userId;
    }
}
