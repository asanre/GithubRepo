package com.example.asanre.githubrepo.domain.useCase;

import com.example.asanre.githubrepo.domain.Provider;
import com.example.asanre.githubrepo.domain.model.IRepository;

import java.util.List;

import io.reactivex.Single;

public class GetRepositories implements UseCase<Single<List<IRepository>>, Integer> {

    @Override
    public Single<List<IRepository>> execute(Integer page) {

        return Provider.getInstance().getRepositories(page);
    }
}
