package com.example.asanre.githubrepo.domain;

import com.example.asanre.githubrepo.data.model.RepoEntity;

import java.util.List;

import io.reactivex.Single;

public interface DataSource {

    /**
     * fetch repos
     *
     * @param page int
     * @return a list of repos
     */
    Single<List<RepoEntity>> getRepos(int page);
}
