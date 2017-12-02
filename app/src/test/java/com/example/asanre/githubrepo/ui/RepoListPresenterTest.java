package com.example.asanre.githubrepo.ui;

import com.example.asanre.githubrepo.domain.model.IRepository;
import com.example.asanre.githubrepo.domain.model.Repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RepoListPresenter.class, RepoListView.class})
public class RepoListPresenterTest {

    @Mock
    RepoListView view;

    private RepoListPresenter spy;

    @Before
    public void setup() throws Exception {

        spy = PowerMockito.spy(new RepoListPresenter(view));
    }

    @Test
    public void given_longItemClicked_then_showDialog() throws Exception {

        IRepository repository = new Repository();
        spy.onRepoLongClicked(repository);
        verify(view).showDialog(any());
    }

    @Test
    public void given_optionSelected_when_selectRepoOption_then_navigateToRepoUrl()
            throws Exception {

        final String repoUrl = "www.repo.com";
        final String ownerUrl = "www.owner.com";

        setClickedRepository(repoUrl, ownerUrl);
        spy.onOptionSelected(0);
        verify(view).navigateToPage(repoUrl);

    }

    @Test
    public void given_optionSelected_when_selectOwnerOption_then_navigateToOwnerUrl()
            throws Exception {

        final String repoUrl = "www.repo.com";
        final String ownerUrl = "www.owner.com";

        setClickedRepository(repoUrl, ownerUrl);
        spy.onOptionSelected(1);
        verify(view).navigateToPage(ownerUrl);

    }

    @Test
    public void given_fetchReposSuccess_when_responseHasValue_then_setAdapterData()
            throws Exception {

        List<IRepository> repos = new ArrayList<>();
        repos.add(new Repository());
        spy.onSuccessHandler(repos);

        verify(view).setAdapterData(repos);
        verify(view).hideLoading();
    }

    @Test
    public void given_fetchReposSuccess_when_responseIsEmptyAndCurrentPageIsGraterThanOne_then_setLastPageTrue()
            throws Exception {

        List<IRepository> repos = new ArrayList<>();
        Whitebox.setInternalState(spy, "currentPage", 2);
        spy.onSuccessHandler(repos);
        boolean lastPage = Whitebox.getInternalState(spy, "isLastPage");
        verify(view, times(0)).setAdapterData(repos);
        verify(view).hideLoading();
        assertTrue(lastPage);
    }

    @Test
    public void given_fetchReposSuccess_when_responseIsEmptyAndCurrentPageIsEqualToOne_then_justHideLoading()
            throws Exception {

        List<IRepository> repos = new ArrayList<>();
        spy.onSuccessHandler(repos);
        boolean lastPage = Whitebox.getInternalState(spy, "isLastPage");
        verify(view, times(0)).setAdapterData(repos);
        verify(view).hideLoading();
        assertFalse(lastPage);
    }

    @Test
    public void given_fetchReposError_then_hideLoadingAndShowErrorMessage() throws Exception {

        String error = "invalid token";
        spy.onErrorHandler(error);
        verify(view).hideLoading();
        verify(view).showErrorMessage(error);
    }

    private void setClickedRepository(String repoUrl, String ownerUrl) {

        Repository repository = new Repository();
        repository.setRepoUrl(repoUrl);
        repository.setOwnerUrl(ownerUrl);
        Whitebox.setInternalState(spy, "repositoryClicked", repository);
    }
}