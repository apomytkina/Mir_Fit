package com.mirfit.mirfit.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@JsonAutoDetect
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Product {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private int price;

    private String currencyCode;

    private String category;

    public Product() {
    }

    public String getCategory() {
        return category;
    }

    public Product(int id, String name, int price, String currencyCode, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.currencyCode = currencyCode;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}
