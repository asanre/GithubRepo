package com.example.asanre.githubrepo.ui;

import com.example.asanre.githubrepo.domain.useCase.GetRepositories;
import com.example.asanre.githubrepo.ui.base.BasePresenter;
import com.example.asanre.githubrepo.ui.base.BaseView;

public class RepoListPresenter extends BasePresenter {

    private final RepoListView view;
    private final GetRepositories getRepositories;
    private int currentPage;

    public RepoListPresenter(RepoListView view) {

        this.view = view;
        currentPage = 1;
        getRepositories = new GetRepositories();
    }

    @Override
    public BaseView getView() {

        return view;
    }

    public void loadRepos() {

    }
}
