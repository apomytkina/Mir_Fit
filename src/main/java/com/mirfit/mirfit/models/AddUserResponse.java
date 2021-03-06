package com.mirfit.mirfit.models;

import java.util.UUID;

public class AddUserResponse {

    private String error;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private UUID id;

    public AddUserResponse(String error, UUID id) {
        this.id = id;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
