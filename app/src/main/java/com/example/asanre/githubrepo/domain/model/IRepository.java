package com.example.asanre.githubrepo.domain.model;

public interface IRepository {

    String getName();

    String getDescription();

    String getRepoUrl();

    boolean hasFork();

    String getOwnerLogin();

    String getOwnerUrl();
}
