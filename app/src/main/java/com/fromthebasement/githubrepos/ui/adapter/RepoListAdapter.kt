package com.fromthebasement.githubrepos.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.fromthebasement.githubrepos.R
import com.fromthebasement.githubrepos.base.BasePagedAdapter
import com.fromthebasement.githubrepos.base.BindingViewHolder
import com.fromthebasement.githubrepos.databinding.RepositoryItemBinding
import com.fromthebasement.githubrepos.model.RepoShort
import timber.log.Timber

/**
 * Manages the exhibition of the items of a [RepoShort] list
 */
class RepoListAdapter(private val adapterOnClick: RepoListAdapterOnClick) :
    BasePagedAdapter<RepoShort, RepositoryItemBinding>(DiffCallback()) {

    override val layoutId: Int = R.layout.repository_item

    override fun onBindViewHolder(holder: BindingViewHolder<RepositoryItemBinding>, position: Int) {
        val item = getItem(position)
        (holder.binding).apply {
            repo = item
            cardView.setOnClickListener {
                item?.run {
                    adapterOnClick.onClick(Pair(item.name, item.owner.name))
                } ?: run {
                    Timber.d("Something wrong with clicked item in position %s", position)
                }
            }
            executePendingBindings()
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<RepoShort>() {

    override fun areItemsTheSame(
        oldItem: RepoShort,
        newItem: RepoShort
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: RepoShort,
        newItem: RepoShort
    ): Boolean {
        return oldItem == newItem
    }
}

interface RepoListAdapterOnClick {
    fun onClick(repo: Pair<String, String>)
}