package com.thesaugat.studentfinance.server;

public class ServerRequest {

    private String operation;
    private UserPojo user;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(UserPojo user) {
        this.user = user;
    }
}