package com.mirfit.mirfit.models;

public class GetBonusesResponse {
    private String error;
    private double bonuses;

    public double getBonuses() {
        return bonuses;
    }

    public void setBonuses(double bonuses) {
        this.bonuses = bonuses;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public GetBonusesResponse(String error, double bonuses) {
        this.error = error;
        this.bonuses = bonuses;
    }
}
