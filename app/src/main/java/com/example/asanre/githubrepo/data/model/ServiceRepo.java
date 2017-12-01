package com.example.asanre.githubrepo.data.model;

import com.google.gson.annotations.SerializedName;

public class ServiceRepo {

    private String name;
    @SerializedName("html_url")
    private String repoUrl;
    private String description;
    @SerializedName("owner")
    private ServiceOwner repoOwner;
    private boolean fork;

    public ServiceRepo(String name, String repoUrl, String description, ServiceOwner repoOwner,
                       boolean fork) {

        this.name = name;
        this.repoUrl = repoUrl;
        this.description = description;
        this.repoOwner = repoOwner;
        this.fork = fork;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getRepoUrl() {

        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {

        this.repoUrl = repoUrl;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public ServiceOwner getRepoOwner() {

        return repoOwner;
    }

    public void setRepoOwner(ServiceOwner repoOwner) {

        this.repoOwner = repoOwner;
    }

    public boolean isFork() {

        return fork;
    }

    public void setFork(boolean fork) {

        this.fork = fork;
    }
}
