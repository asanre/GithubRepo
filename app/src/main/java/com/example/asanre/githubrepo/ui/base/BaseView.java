package com.example.asanre.githubrepo.ui.base;

public interface BaseView {

    void showLoading();

    void hideLoading();

    boolean isViewAlive();

    void showErrorMessage(String errorMessage);
}
