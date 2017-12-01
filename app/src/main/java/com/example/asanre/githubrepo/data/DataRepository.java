package com.example.asanre.githubrepo.data;

import android.content.Context;

import com.example.asanre.githubrepo.data.database.GithubDao;
import com.example.asanre.githubrepo.data.database.GithubDb;
import com.example.asanre.githubrepo.data.model.RepoEntity;
import com.example.asanre.githubrepo.data.model.ServiceRepo;
import com.example.asanre.githubrepo.data.restService.ApiService;
import com.example.asanre.githubrepo.data.restService.RestClient;
import com.example.asanre.githubrepo.domain.Repository;

import java.util.List;

import io.reactivex.Single;

public class DataRepository implements Repository {

    private final GithubDao githubDao;
    private final ApiService apiService;
    private static DataRepository sInstance;

    public DataRepository(Context context) {

        this.githubDao = GithubDb.getInstance(context).getGithubDao();
        this.apiService = RestClient.getService();
    }

    public static DataRepository getInstance(Context context) {

        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(context);
                }
            }
        }
        return sInstance;
    }

    /**
     * fetch repos from github if success save it on db onError
     * but on error fetch from cached
     *
     * @param page int
     * @return list of repo entities
     */
    @Override
    public Single<List<RepoEntity>> getRepos(final int page) {

        return Single.zip(retrieveReposAndSaveOnSuccess(page), githubDao.getReposByPage(page),
                (apiRepo, dbRepo) -> {

                    if (apiRepo.isEmpty()) {
                        return dbRepo;
                    }
                    return apiRepo;
                });
    }

    private Single<List<RepoEntity>> retrieveReposAndSaveOnSuccess(int page) {

        return getCloudRepos(page).map(serviceRepos -> DataMapper.mapToEntity(serviceRepos, page))
                .doOnSuccess(this::saveOnDB);
    }

    private Single<List<ServiceRepo>> getCloudRepos(int page) {

        final String user = "xing";
        final int reposPerPage = 10;
        return apiService.getUserRepos(user, reposPerPage, page);
    }

    private void saveOnDB(List<RepoEntity> repoEntities) {

        RepoEntity[] repos = repoEntities.toArray(new RepoEntity[repoEntities.size()]);
        githubDao.insert(repos);
    }
}
