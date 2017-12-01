package com.example.asanre.githubrepo.data.restService;

import com.example.asanre.githubrepo.data.model.ServiceRepo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("users/{user}/repos")
    Single<List<ServiceRepo>> getUserRepos(@Path("user") String user,
                                           @Query("per_page") int repoPerPage,
                                           @Query("page") int page);
}
