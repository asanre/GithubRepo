package com.example.asanre.githubrepo.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.asanre.githubrepo.R;
import com.example.asanre.githubrepo.ui.base.BaseFragment;

import butterknife.BindView;

public class RepoListFragment extends BaseFragment implements RepoListView {

    @BindView(R.id.rv_repositories)
    RecyclerView recyclerView;
    @BindView(R.id.pb_loading)
    ProgressBar loading;

    private RepoListAdapter adapter;
    private RepoListPresenter presenter;

    @Override
    protected int getFragmentLayout() {

        return R.layout.fragment_repo_list;
    }

    @Override
    protected void prepareView(View view) {

        setUpRecycler();
        presenter = new RepoListPresenter(this);
        presenter.loadRepos();
    }

    @Override
    public void showLoading() {

        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        loading.setVisibility(View.GONE);
    }

    private void setUpRecycler() {

        adapter = new RepoListAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
