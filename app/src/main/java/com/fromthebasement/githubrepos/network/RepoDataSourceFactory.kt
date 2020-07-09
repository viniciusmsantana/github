package com.fromthebasement.githubrepos.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.fromthebasement.githubrepos.model.RepoShort
import com.fromthebasement.githubrepos.ui.ViewState
import javax.inject.Inject

/**
 * Sets the configurations of the pagination done by the [dataSource]
 */
class RepoDataSourceFactory @Inject constructor(
    private val dataSource: RepoDataSource
) : DataSource.Factory<Int, RepoShort>() {

    lateinit var paginationState : MutableLiveData<ViewState>

    override fun create(): DataSource<Int, RepoShort> {
        dataSource.paginationState = paginationState
        return dataSource
    }

    companion object {
        private const val PAGE_SIZE = 20

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }

}