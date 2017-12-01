package com.example.asanre.githubrepo.domain.useCase;

import com.example.asanre.githubrepo.domain.Provider;
import com.example.asanre.githubrepo.domain.model.IRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GetRepositories implements UseCase<List<IRepository>, Integer> {

    @Override
    public void execute(BaseObserver<List<IRepository>> observer, Integer page) {

        Provider.getRepositories(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
