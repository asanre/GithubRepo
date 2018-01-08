package com.example.asanre.githubrepo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.asanre.githubrepo.R;
import com.example.asanre.githubrepo.domain.Provider;

public class RepoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Provider.getInstance().init(this);
        setContentView(R.layout.activity_repo_list);
    }
}
