package com.fromthebasement.githubrepos.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fromthebasement.githubrepos.model.PullRequest
import com.fromthebasement.githubrepos.model.RepoShort
import com.fromthebasement.githubrepos.model.Result
import com.fromthebasement.githubrepos.repository.GithubRepository
import com.fromthebasement.githubrepos.ui.ViewState
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Exposes a list of [PullRequest] and manages its state
 */
class RepoDetailsViewModel @ViewModelInject constructor(
    private val githubRepo: GithubRepository
) : ViewModel() {

    val viewState = MutableLiveData<ViewState>()
    private val _pullRequestList = MutableLiveData<List<PullRequest>>()
    val pullRequestList: LiveData<List<PullRequest>> = _pullRequestList

    init {
        Timber.d("init RepoDetailsViewModel")
    }

    /** Fetchs the [PullRequest]s based on the [repoName] and [repoAuthor] */
    fun fetchData(repoName: String, repoAuthor: String) {
        viewModelScope.launch {
            viewState.value = ViewState.Loading
            when (val result = githubRepo.fetchRepoPullRequests(repoName, repoAuthor)) {
                is Result.Success -> {
                    if (result.data.isNotEmpty()) {
                        _pullRequestList.value = result.data
                        viewState.value = ViewState.Content
                    } else {
                        viewState.value = ViewState.Empty
                    }
                }
                is Result.Error -> {
                    viewState.value = ViewState.Error(result.message)
                }
            }

        }

    }
}