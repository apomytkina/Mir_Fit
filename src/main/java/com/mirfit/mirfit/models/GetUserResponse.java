package com.mirfit.mirfit.models;

public class GetUserResponse {

    private User user;
    private String error;

    public GetUserResponse(User user, String error) {
        this.user = user;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public User getUser() {
        return user;
    }
}
