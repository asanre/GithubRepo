package com.example.asanre.githubrepo.ui;

import com.example.asanre.githubrepo.domain.model.IRepository;
import com.example.asanre.githubrepo.domain.useCase.BaseObserver;
import com.example.asanre.githubrepo.domain.useCase.GetRepositories;
import com.example.asanre.githubrepo.ui.base.BasePresenter;
import com.example.asanre.githubrepo.ui.base.BaseView;

import java.util.List;

import static com.example.asanre.githubrepo.ui.utils.DialogOptions.OWNER;
import static com.example.asanre.githubrepo.ui.utils.DialogOptions.REPO;

public class RepoListPresenter extends BasePresenter {

    private final RepoListView view;
    private final GetRepositories getRepositories;
    private int currentPage;
    private boolean isLastPage;
    private IRepository repositoryClicked;
    private CharSequence options[] = new CharSequence[]{REPO.getValue(), OWNER.getValue()};

    public RepoListPresenter(RepoListView view) {

        this.view = view;
        currentPage = 1;
        isLastPage = false;
        getRepositories = new GetRepositories();
    }

    @Override
    public BaseView getView() {

        return view;
    }

    void loadRepos() {

        fetchRepos(currentPage);
    }

    void loadMore() {

        if (!isLastPage) {
            fetchRepos(++currentPage);
        }
    }

    void onRepoLongClicked(IRepository repository) {

        this.repositoryClicked = repository;
        view.showDialog(options);

    }

    void onOptionSelected(int position) {

        String url = options[position].equals(REPO.getValue())
                     ? repositoryClicked.getRepoUrl()
                     : repositoryClicked.getOwnerUrl();
        view.navigateToPage(url);
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
                    onErrorHandler(error.getLocalizedMessage());
                }
            }
        }, page);
    }

    void onSuccessHandler(List<IRepository> repositories) {

        if (repositories.isEmpty()) {
            isLastPage = true;
        } else {
            view.setAdapterData(repositories);
            view.hideLoading();
        }

    }

    void onErrorHandler(String message) {

        view.hideLoading();
        view.showErrorMessage(message);
    }
}
