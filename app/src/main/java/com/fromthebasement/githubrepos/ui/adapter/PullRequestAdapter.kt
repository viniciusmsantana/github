package com.fromthebasement.githubrepos.ui.adapter

import com.fromthebasement.githubrepos.R
import com.fromthebasement.githubrepos.base.BaseRecyclerAdapter
import com.fromthebasement.githubrepos.base.BindingViewHolder
import com.fromthebasement.githubrepos.databinding.PullrequestItemBinding
import com.fromthebasement.githubrepos.model.PullRequest
import com.fromthebasement.githubrepos.model.RepoShort

/**
 * Manages the exhibition of the items of a [PullRequest] list
 */
class PullRequestAdapter : BaseRecyclerAdapter<PullRequest, PullrequestItemBinding>(emptyList()) {

    override val layoutId = R.layout.pullrequest_item

    override fun onBindViewHolder(
        holder: BindingViewHolder<PullrequestItemBinding>,
        position: Int
    ) {
        holder.binding.pr = listItems[position]
    }
}