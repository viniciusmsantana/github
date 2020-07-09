package com.fromthebasement.githubrepos.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple Base for RecyclerAdapters to make my life easier
 */
abstract class BaseRecyclerAdapter<T, DB : ViewDataBinding>(items: List<T>) :
    RecyclerView.Adapter<BindingViewHolder<DB>>() {

    protected var listItems = ArrayList<T>()

    @get:LayoutRes
    protected abstract val layoutId: Int

    init {
        listItems.addAll(items)
    }

    fun setItems(newItems: List<T>?) {
        listItems.clear()

        newItems?.run {
            listItems.addAll(newItems)
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<DB> {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<DB>(inflater, layoutId, parent, false)
        return BindingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}