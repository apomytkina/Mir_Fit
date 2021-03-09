package com.mirfit.mirfit.models;

import java.util.UUID;

public class UpdateLoginRequest {

    private UUID id;
    private String login;

    public UUID getId() { return id;}

    public String getLogin() {
        return login;
    }
}
