package com.mirfit.mirfit.models;

public class UpdateNameRequest {
    private final String name;
    private final String cardNumber;

    public UpdateNameRequest(String name, String cardNumber) {
        this.name = name;
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }
}
