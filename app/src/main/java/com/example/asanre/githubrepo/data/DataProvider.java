package com.example.asanre.githubrepo.data;

import android.content.Context;

import com.example.asanre.githubrepo.data.database.GithubDao;
import com.example.asanre.githubrepo.data.database.GithubDb;
import com.example.asanre.githubrepo.data.model.RepoEntity;
import com.example.asanre.githubrepo.data.model.ServiceRepo;
import com.example.asanre.githubrepo.data.restService.ApiService;
import com.example.asanre.githubrepo.data.restService.RestClient;
import com.example.asanre.githubrepo.domain.DataSource;
import com.example.asanre.githubrepo.domain.RepoParams;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class DataProvider implements DataSource {

    private final GithubDao githubDao;
    private final ApiService apiService;
    private static DataProvider sInstance;

    public DataProvider(Context context) {

        this.githubDao = GithubDb.getInstance(context).getGithubDao();
        this.apiService = RestClient.getService();
    }

    public static DataProvider getInstance(Context context) {

        if (sInstance == null) {
            synchronized (DataProvider.class) {
                if (sInstance == null) {
                    sInstance = new DataProvider(context);
                }
            }
        }
        return sInstance;
    }

    /**
     * fetch repos from github if success save it on db
     * but on error fetch from cached
     *
     * @param page int
     * @return list of repo entities
     */
    @Override
    public Single<List<RepoEntity>> getRepos(final int page) {

        return Single.zip(retrieveReposAndSaveOnSuccess(page).onErrorReturnItem(new ArrayList<>()),
                githubDao.getReposByPage(page), (apiRepo, dbRepo) -> {

                    if (apiRepo.isEmpty()) {
                        return dbRepo;
                    }
                    return apiRepo;
                });
    }

    private Single<List<RepoEntity>> retrieveReposAndSaveOnSuccess(int page) {

        return getCloudRepos(new RepoParams(page)).map(
                serviceRepos -> DataMapper.mapToEntity(serviceRepos, page))
                .doOnSuccess(this::saveOnDB);
    }

    private Single<List<ServiceRepo>> getCloudRepos(RepoParams params) {

        return apiService.getUserRepos(params.getUser(), params.getReposPerPage(),
                params.getPage());
    }

    private void saveOnDB(List<RepoEntity> repoEntities) {

        RepoEntity[] repos = repoEntities.toArray(new RepoEntity[repoEntities.size()]);
        githubDao.insert(repos);
    }
}
