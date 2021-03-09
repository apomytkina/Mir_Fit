package com.mirfit.mirfit.models;

import java.util.UUID;

public class UpdatePasswordRequest {

    private UUID id;
    private String password;

    public UUID getId() { return id;}

    public String getPassword() {
        return password;
    }
}
