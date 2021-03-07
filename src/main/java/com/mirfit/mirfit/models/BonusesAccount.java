package com.mirfit.mirfit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class BonusesAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private UUID userId;
    private double numberOfBonuses;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public BonusesAccount() {
    }

    public BonusesAccount(long id, double numberOfBonuses, UUID userId) {
        this.id = id;
        this.numberOfBonuses = numberOfBonuses;
        this.userId = userId;
    }

    public double getNumberOfBonuses() {
        return numberOfBonuses;
    }

    public void setNumberOfBonuses(double numberOfBonuses) {
        this.numberOfBonuses = numberOfBonuses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
