package com.mirfit.mirfit.models;

public class UpdateBonusesRequest {
    private final double numberOfBonuses;
    private final String cardNumber;

    public UpdateBonusesRequest(String cardNumber, double numberOfBonuses) {
        this.numberOfBonuses = numberOfBonuses;
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getNumberOfBonuses() {
        return numberOfBonuses;
    }
}
