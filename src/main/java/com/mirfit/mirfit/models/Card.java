package com.mirfit.mirfit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private UUID userId;
    private double numberOfBonuses;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Card() {
    }

    public Card(long id, double numberOfBonuses, UUID userId, String name, String number) {
        this.id = id;
        this.numberOfBonuses = numberOfBonuses;
        this.userId = userId;
        this.name = name;
        this.number = number;
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
