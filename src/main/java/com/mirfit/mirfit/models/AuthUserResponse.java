package com.mirfit.mirfit.models;

public class AuthUserResponse {

    private long id;
    private String error;

    public AuthUserResponse(long id, String error) {
        this.id = id;
        this.error = error;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getId() {
        return id;
    }

    public String getError() {
        return error;
    }
}
