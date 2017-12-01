package com.example.asanre.githubrepo;

import android.app.Application;

import com.example.asanre.githubrepo.domain.Provider;
import com.facebook.stetho.Stetho;

public class GithubApp extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        initStetho();
        Provider.init(this);
    }

    private void initStetho() {

        Stetho.initializeWithDefaults(this);
    }
}
