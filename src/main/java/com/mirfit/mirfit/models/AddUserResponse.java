package com.mirfit.mirfit.models;

public class AddUserResponse {

    private String error;

    public AddUserResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
