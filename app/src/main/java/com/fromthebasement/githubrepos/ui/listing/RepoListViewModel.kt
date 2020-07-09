package com.fromthebasement.githubrepos.ui.listing

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.fromthebasement.githubrepos.model.RepoShort
import com.fromthebasement.githubrepos.repository.GithubRepository
import com.fromthebasement.githubrepos.ui.ViewState
import timber.log.Timber

/**
 * Exposes a paginated list of [RepoShort] and manages its state
 */
class RepoListViewModel @ViewModelInject constructor(
    githubRepo: GithubRepository
) : ViewModel() {

    val paginationState = MutableLiveData<ViewState>().apply { value =
        ViewState.Loading
    }

    val repoList: LiveData<PagedList<RepoShort>> =
        githubRepo.fetchRepoList(paginationState)

    init {
        Timber.d("init RepoListViewModel")
    }
}