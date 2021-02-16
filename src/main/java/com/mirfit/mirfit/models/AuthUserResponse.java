package com.mirfit.mirfit.models;

public class AuthUserResponse {

    private String error;
    private User user;

    public AuthUserResponse(User user, String error) {
        this.user = user;
        this.error = error;
    }

    public User getUser() { return user; }

    public String getError() {
        return error;
    }
}
