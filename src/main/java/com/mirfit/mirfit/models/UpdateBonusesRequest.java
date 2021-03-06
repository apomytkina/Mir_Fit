package com.mirfit.mirfit.models;

import java.util.UUID;

public class UpdateBonusesRequest {
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    private UUID userId;
    private double numberOfBonuses;

    public UpdateBonusesRequest(UUID userId, double numberOfBonuses) {
        this.numberOfBonuses = numberOfBonuses;
        this.userId = userId;
    }

    public double getNumberOfBonuses() {
        return numberOfBonuses;
    }

    public void setNumberOfBonuses(double numberOfBonuses) {
        this.numberOfBonuses = numberOfBonuses;
    }
}
