package com.example.asanre.githubrepo.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.asanre.githubrepo.data.model.RepoEntity;

import java.util.List;

import io.reactivex.Single;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface GithubDao {

    @Insert(onConflict = REPLACE)
    void insert(RepoEntity... repoEntities);

    @Query("select * from repository where page = :page")
    Single<List<RepoEntity>> getReposByPage(int page);
}
