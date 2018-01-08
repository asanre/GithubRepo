package com.example.asanre.githubrepo.domain;

import android.content.Context;

import com.example.asanre.githubrepo.data.DataProvider;
import com.example.asanre.githubrepo.domain.model.IRepository;

import java.util.List;

import io.reactivex.Single;

public class Provider {

    private DataSource dataSource;
    private static Provider sInstance;

    public static Provider getInstance() {

        if (sInstance == null) {
            synchronized (DataProvider.class) {
                if (sInstance == null) {
                    sInstance = new Provider();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context) {

        this.dataSource = DataProvider.getInstance(context);
    }

    public Single<List<IRepository>> getRepositories(int page) {

        return dataSource.getRepos(page).map(DomainMapper::map);
    }
}
