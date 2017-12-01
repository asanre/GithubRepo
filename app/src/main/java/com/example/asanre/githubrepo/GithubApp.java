package com.example.asanre.githubrepo;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class GithubApp extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        initStetho();
    }

    private void initStetho() {

        Stetho.initializeWithDefaults(this);
    }
}
