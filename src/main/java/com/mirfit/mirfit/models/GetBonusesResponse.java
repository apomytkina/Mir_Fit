package com.mirfit.mirfit.models;

public class GetBonusesResponse {
    private Bonuses bonuses;
    private String error;

    public GetBonusesResponse() { }

    public GetBonusesResponse(String error, Bonuses bonuses) {
        this.error = error;
        this.bonuses = bonuses;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Bonuses getBonuses() {
        return bonuses;
    }

    public void setBonuses(Bonuses bonuses) {
        this.bonuses = bonuses;
    }
}
