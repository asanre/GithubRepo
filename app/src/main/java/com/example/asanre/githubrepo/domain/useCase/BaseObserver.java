package com.example.asanre.githubrepo.domain.useCase;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public interface BaseObserver<T> extends SingleObserver<T> {

    @Override
    default void onSubscribe(Disposable disposable) {

    }

    @Override
    void onSuccess(T response);

    @Override
    void onError(Throwable error);
}
