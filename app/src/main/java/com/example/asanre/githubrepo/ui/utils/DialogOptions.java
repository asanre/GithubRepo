package com.example.asanre.githubrepo.ui.utils;

public enum DialogOptions {
    REPO("Repo"), OWNER("Owner");

    String value;

    DialogOptions(String value) {

        this.value = value;
    }

    public String getValue() {

        return this.value;
    }
}
