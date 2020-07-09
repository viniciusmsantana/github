package com.fromthebasement.githubrepos.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fromthebasement.githubrepos.model.PullRequest
import com.fromthebasement.githubrepos.model.RepoShort
import com.fromthebasement.githubrepos.model.Result
import com.fromthebasement.githubrepos.model.request
import com.fromthebasement.githubrepos.network.RepoDataSourceFactory
import com.fromthebasement.githubrepos.network.api.GithubApi
import com.fromthebasement.githubrepos.ui.ViewState
import timber.log.Timber
import javax.inject.Inject

/**
 * Manages all accesses to the GithubApi
 */
class GithubRepository @Inject constructor(
    private val githubPageDataSourceFactory: RepoDataSourceFactory,
    private val githubApi: GithubApi
) {

    /** Fetches a paginated list of RepoShort */
    fun fetchRepoList(paginationState: MutableLiveData<ViewState>)
            : LiveData<PagedList<RepoShort>> {
        Timber.d("fetch repo list")

        githubPageDataSourceFactory.paginationState = paginationState
        return LivePagedListBuilder(
            githubPageDataSourceFactory,
            RepoDataSourceFactory.pagedListConfig()
        ).build()
    }

    /** Fetches a list of PullRequest base on the [repoName] and [repoAuthor] */
    suspend fun fetchRepoPullRequests(repoName: String, repoAuthor: String): Result<List<PullRequest>> {
        return request { githubApi.fetchPullRequests(repoName, repoAuthor) }
    }
}