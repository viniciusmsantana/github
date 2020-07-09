package com.fromthebasement.githubrepos.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.fromthebasement.githubrepos.model.RepoShort
import com.fromthebasement.githubrepos.model.Result
import com.fromthebasement.githubrepos.model.request
import com.fromthebasement.githubrepos.network.api.GithubApi
import com.fromthebasement.githubrepos.ui.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * DataSource used to fetch a paginated list of [RepoShort]
 * Manages the state of the pagination
 */
class RepoDataSource @Inject constructor(
    private val api: GithubApi
) : PageKeyedDataSource<Int, RepoShort>() {

    lateinit var paginationState: MutableLiveData<ViewState>

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RepoShort>
    ) {
        fetchData(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, RepoShort>
    ) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, (page + 1))
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, RepoShort>
    ) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, (page - 1))
        }
    }

    private fun fetchData(
        page: Int,
        callback: (List<RepoShort>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            paginationState.postValue(ViewState.Loading)
            when (val result = request { api.fetchRepoList(page = page) }) {
                is Result.Success -> {
                    callback(result.data.items)
                    paginationState.postValue(ViewState.Content)
                }
                is Result.Error -> {
                    paginationState.postValue(ViewState.Error(result.message))
                }
            }
        }
    }
}