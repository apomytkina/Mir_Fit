package com.mirfit.mirfit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Bonuses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double numberOfBonuses;

    public Bonuses() { }

    public Bonuses(long id, double numberOfBonuses) {
        this.id = id;
        this.numberOfBonuses = numberOfBonuses;
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
