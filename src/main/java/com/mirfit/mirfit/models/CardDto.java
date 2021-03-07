package com.mirfit.mirfit.models;

public class CardDto {
    private String name;
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardDto(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
