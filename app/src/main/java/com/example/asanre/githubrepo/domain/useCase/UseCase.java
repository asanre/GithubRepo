package com.example.asanre.githubrepo.domain.useCase;

public interface UseCase<RESPONSE_DATA, REQUEST_DATA> {

    RESPONSE_DATA execute(REQUEST_DATA params);
}
