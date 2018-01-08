package com.example.asanre.githubrepo.domain.useCase;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public interface BaseObserver<T> extends SingleObserver<T> {

    /**
     * this is not the best approach, but in the moment we are leaving this
     * here because we are not handling rotations
     *
     * @param disposable
     */
    @Override
    default void onSubscribe(Disposable disposable) {

    }

    @Override
    void onSuccess(T response);

    @Override
    void onError(Throwable error);
}
