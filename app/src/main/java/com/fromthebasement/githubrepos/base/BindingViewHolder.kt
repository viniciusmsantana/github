package com.fromthebasement.githubrepos.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * A ViewHolder for Adapters that make use of DataBinding for its items
 */
class BindingViewHolder<DB : ViewDataBinding>(val binding: DB) :
    RecyclerView.ViewHolder(binding.root)