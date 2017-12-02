package com.example.asanre.githubrepo.ui;

import com.example.asanre.githubrepo.domain.model.IRepository;
import com.example.asanre.githubrepo.ui.base.BaseView;

import java.util.List;

public interface RepoListView extends BaseView {

    void setAdapterData(List<IRepository> repositories);

    void showDialog(CharSequence[] options);

    void navigateToPage(String url);
}
