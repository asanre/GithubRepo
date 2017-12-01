package com.example.asanre.githubrepo.ui;

import com.example.asanre.githubrepo.domain.model.IRepository;
import com.example.asanre.githubrepo.domain.useCase.BaseObserver;
import com.example.asanre.githubrepo.domain.useCase.GetRepositories;
import com.example.asanre.githubrepo.ui.base.BasePresenter;
import com.example.asanre.githubrepo.ui.base.BaseView;

import java.util.List;

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

        fetchRepos(currentPage);
    }

    private void fetchRepos(int page) {

        view.showLoading();
        getRepositories.execute(new BaseObserver<List<IRepository>>() {

            @Override
            public void onSuccess(List<IRepository> repositories) {

                if (isViewAlive()) {
                    onSuccessHandler(repositories);
                }
            }

            @Override
            public void onError(Throwable error) {

                if (isViewAlive()) {
                    onErrorHandler(error.getMessage());
                }
            }
        }, page);
    }

    void onSuccessHandler(List<IRepository> repositories) {

        view.setAdapterData(repositories);
        view.hideLoading();

    }

    void onErrorHandler(String message) {

        view.hideLoading();
        view.showErrorMessage(message);
    }
}
