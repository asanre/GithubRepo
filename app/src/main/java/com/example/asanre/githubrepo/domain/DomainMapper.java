package com.example.asanre.githubrepo.domain;

import com.example.asanre.githubrepo.data.model.RepoEntity;
import com.example.asanre.githubrepo.domain.model.IRepository;
import com.example.asanre.githubrepo.domain.model.Repository;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DomainMapper {

    public static List<IRepository> map(List<RepoEntity> repos) {

        ModelMapper mapper = new ModelMapper();
        List<IRepository> repositories = new ArrayList<>();
        for (RepoEntity repo : repos) {
            repositories.add(mapper.map(repo, Repository.class));
        }

        return repositories;
    }
}
