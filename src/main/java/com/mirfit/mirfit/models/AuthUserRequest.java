package com.mirfit.mirfit.models;

public class AuthUserRequest {

    String login;
    String password;

    public AuthUserRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
