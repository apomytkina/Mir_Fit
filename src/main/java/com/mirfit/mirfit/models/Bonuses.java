package com.mirfit.mirfit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Bonuses {
    @Id
    private UUID id;
    private double numberOfBonuses;

    public Bonuses() {
    }

    public Bonuses(UUID id, double numberOfBonuses) {
        this.id = id;
        this.numberOfBonuses = numberOfBonuses;
    }

    public double getNumberOfBonuses() {
        return numberOfBonuses;
    }

    public void setNumberOfBonuses(double numberOfBonuses) {
        this.numberOfBonuses = numberOfBonuses;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
