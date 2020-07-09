package com.fromthebasement.githubrepos.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 * A simple Base for PagedAdapters to make my life easier
 */
abstract class BasePagedAdapter<T, DB : ViewDataBinding>(diffCallback: DiffUtil.ItemCallback<T>) :
    PagedListAdapter<T, BindingViewHolder<DB>>(diffCallback) {

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<DB> {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<DB>(inflater, layoutId, parent, false)
        return BindingViewHolder(binding)
    }
}