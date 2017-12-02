package com.example.asanre.githubrepo.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.asanre.githubrepo.R;
import com.example.asanre.githubrepo.domain.model.IRepository;
import com.example.asanre.githubrepo.ui.base.BaseFragment;
import com.example.asanre.githubrepo.ui.utils.AppUtils;

import java.util.List;

import butterknife.BindView;

public class RepoListFragment extends BaseFragment
        implements RepoListView, RepoListAdapter.AdapterOnClickListener,
        DialogInterface.OnClickListener {

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
        setScrollListener();
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

    @Override
    public void setAdapterData(List<IRepository> repositories) {

        adapter.addRepositories(repositories);
    }

    @Override
    public void onLongClick(IRepository repository) {

        presenter.onRepoLongClicked(repository);
    }

    @Override
    public void showDialog(CharSequence[] options) {

        AlertDialog.Builder builder = createDialog(options);
        builder.show();
    }

    @NonNull
    private AlertDialog.Builder createDialog(CharSequence[] options) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.dialog_title));
        builder.setItems(options, this);
        return builder;
    }

    @Override
    public void navigateToPage(String url) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int optionClicked) {

        presenter.onOptionSelected(optionClicked);
    }

    private void setUpRecycler() {

        adapter = new RepoListAdapter(getActivity(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setScrollListener() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (AppUtils.pageEndlessDetect(recyclerView)) {
                    presenter.loadMore();
                }
            }
        });
    }
}
