package com.example.asanre.githubrepo.data;

import com.example.asanre.githubrepo.data.model.RepoEntity;
import com.example.asanre.githubrepo.data.model.ServiceRepo;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DataMapper {

    public static List<RepoEntity> mapToEntity(List<ServiceRepo> serviceRepos, int page) {

        ModelMapper mapper = new ModelMapper();
        List<RepoEntity> repoEntities = new ArrayList<>();
        for (ServiceRepo repo : serviceRepos) {
            RepoEntity repoEntity = mapper.map(repo, RepoEntity.class);
            repoEntity.setPage(page);
            repoEntities.add(repoEntity);
        }

        return repoEntities;
    }
}
