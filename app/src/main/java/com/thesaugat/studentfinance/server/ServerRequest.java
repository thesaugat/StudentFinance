package com.thesaugat.studentfinance.server;

public class ServerRequest {

    private String operation;
    private String symbolno;
    private UserPojo user;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setSymbolno(String symbolno) {this.symbolno = symbolno; }

    public void setUser(UserPojo user) {
        this.user = user;
    }
}