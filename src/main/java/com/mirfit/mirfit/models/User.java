package com.mirfit.mirfit.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.UUID;

@JsonAutoDetect
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private String cardNumber;
    private String password;
    @Column(unique = true)
    private String login;

    public User() { }

    public User(UUID id, String cardNumber, String firstName,
                String secondName, String patronymic, String password, String login) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.password = password;
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setLogin(String login) { this.login = login; }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() { return login; }
}
