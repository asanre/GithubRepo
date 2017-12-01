package com.example.asanre.githubrepo.data.model;

import com.google.gson.annotations.SerializedName;

public class ServiceOwner {

    private String login;
    @SerializedName("html_url")
    private String ownerUrl;

    public ServiceOwner(String login, String ownerUrl) {

        this.login = login;
        this.ownerUrl = ownerUrl;
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public String getOwnerUrl() {

        return ownerUrl;
    }

    public void setOwnerUrl(String ownerUrl) {

        this.ownerUrl = ownerUrl;
    }
}
