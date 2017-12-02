package com.example.asanre.githubrepo.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "repository", indices = {@Index(value = "page")})
public class RepoEntity {

    @NonNull
    @PrimaryKey
    private String name;
    private String description;
    private String repoUrl;
    private boolean fork;
    private String ownerLogin;
    private String ownerUrl;
    private int page;

    public RepoEntity() {

    }

    public RepoEntity(int id, @NonNull String name, String description, String repoUrl,
                      boolean fork, String ownerLogin, String ownerUrl, int page) {

        this.name = name;
        this.description = description;
        this.repoUrl = repoUrl;
        this.fork = fork;
        this.ownerLogin = ownerLogin;
        this.ownerUrl = ownerUrl;
        this.page = page;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getRepoUrl() {

        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {

        this.repoUrl = repoUrl;
    }

    public boolean isFork() {

        return fork;
    }

    public void setFork(boolean fork) {

        this.fork = fork;
    }

    public String getOwnerLogin() {

        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {

        this.ownerLogin = ownerLogin;
    }

    public String getOwnerUrl() {

        return ownerUrl;
    }

    public void setOwnerUrl(String ownerUrl) {

        this.ownerUrl = ownerUrl;
    }

    public int getPage() {

        return page;
    }

    public void setPage(int page) {

        this.page = page;
    }
}
