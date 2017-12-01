package com.example.asanre.githubrepo.domain.useCase;

public interface UseCase<RESPONSE_DATA, REQUEST_DATA> {

    void execute(BaseObserver<RESPONSE_DATA> observer, REQUEST_DATA params);
}
