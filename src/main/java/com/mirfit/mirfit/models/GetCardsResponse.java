package com.mirfit.mirfit.models;

import java.util.List;

public class GetCardsResponse {
    private List<Card> cards;
    private String error;

    public GetCardsResponse() {
    }

    public GetCardsResponse(String error, List<Card> cards) {
        this.error = error;
        this.cards = cards;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
