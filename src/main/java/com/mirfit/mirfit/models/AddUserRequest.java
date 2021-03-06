package com.mirfit.mirfit.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class AddUserRequest {

    private String firstName;
    private String secondName;
    private String patronymic;
    private String password;
    private String login;

    public AddUserRequest(String firstName, String secondName, String patronymic, String password, String login) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.password = password;
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLogin() {
        return login;
    }
}
