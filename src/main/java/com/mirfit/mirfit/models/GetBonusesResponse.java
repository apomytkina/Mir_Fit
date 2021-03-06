package com.mirfit.mirfit.models;

public class GetBonusesResponse {
    private BonusesAccount bonuses;
    private String error;

    public GetBonusesResponse() {
    }

    public GetBonusesResponse(String error, BonusesAccount bonuses) {
        this.error = error;
        this.bonuses = bonuses;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public BonusesAccount getBonuses() {
        return bonuses;
    }

    public void setBonuses(BonusesAccount bonuses) {
        this.bonuses = bonuses;
    }
}
