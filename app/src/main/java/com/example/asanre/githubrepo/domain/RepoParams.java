package com.example.asanre.githubrepo.domain;

/**
 * create a repo params to leave the possibilities to fetch repos from other users
 */
public class RepoParams {

    private final String user = "xing";
    private final int reposPerPage = 10;
    private final int page;

    public RepoParams(int page) {

        this.page = page;
    }

    public String getUser() {

        return user;
    }

    public int getReposPerPage() {

        return reposPerPage;
    }

    public int getPage() {

        return page;
    }
}
