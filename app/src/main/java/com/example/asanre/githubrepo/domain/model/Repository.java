package com.example.asanre.githubrepo.domain.model;

public class Repository implements IRepository {

    private String name;
    private String description;
    private String repoUrl;
    private boolean fork;
    private String ownerLogin;
    private String ownerUrl;

    public Repository() {

    }

    @Override
    public String getName() {

        return name;
    }

    @Override
    public String getDescription() {

        return description;
    }

    @Override
    public String getRepoUrl() {

        return repoUrl;
    }

    @Override
    public boolean hasFork() {

        return fork;
    }

    @Override
    public String getOwnerLogin() {

        return ownerLogin;
    }

    @Override
    public String getOwnerUrl() {

        return ownerUrl;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public void setRepoUrl(String repoUrl) {

        this.repoUrl = repoUrl;
    }

    public void setFork(boolean fork) {

        this.fork = fork;
    }

    public void setOwnerLogin(String ownerLogin) {

        this.ownerLogin = ownerLogin;
    }

    public void setOwnerUrl(String ownerUrl) {

        this.ownerUrl = ownerUrl;
    }
}
