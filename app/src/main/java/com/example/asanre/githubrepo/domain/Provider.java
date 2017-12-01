package com.example.asanre.githubrepo.domain;

import android.content.Context;

import com.example.asanre.githubrepo.data.DataProvider;
import com.example.asanre.githubrepo.domain.model.IRepository;

import java.util.List;

import io.reactivex.Single;

public class Provider {

    private static DataSource dataSource;

    public static void init(Context context) {

        dataSource = DataProvider.getInstance(context);
    }

    public static Single<List<IRepository>> getRepositories(int page) {

        return dataSource.getRepos(page).map(DomainMapper::map);
    }
}
