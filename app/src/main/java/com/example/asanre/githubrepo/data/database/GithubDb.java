package com.example.asanre.githubrepo.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.asanre.githubrepo.data.model.RepoEntity;

@Database(entities = {RepoEntity.class}, version = 1)
public abstract class GithubDb extends RoomDatabase {

    public static final String DATABASE_NAME = "github";
    private static GithubDb instance;

    public static GithubDb getInstance(Context context) {

        if (instance == null) {
            synchronized (GithubDb.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), GithubDb.class,
                            GithubDb.DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

    public abstract GithubDao getGithubDao();
}
