package com.thesaugat.studentfinance.server;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {
    @SerializedName("result")
    private String result;
    @SerializedName("message")
    private String message;

    private UserPojo user;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public UserPojo getUser() {
        return user;
    }
}

