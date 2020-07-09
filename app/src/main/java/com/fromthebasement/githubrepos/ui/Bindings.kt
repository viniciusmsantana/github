package com.fromthebasement.githubrepos.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fromthebasement.githubrepos.R
import com.fromthebasement.githubrepos.UI.datePattern
import com.fromthebasement.githubrepos.base.BaseRecyclerAdapter
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/** Defines the item source of a PagedListAdapter */
@BindingAdapter("pagedList")
fun <T, A : PagedListAdapter<T, out ViewDataBinding>> setPagedList(
    recyclerView: RecyclerView,
    itemList: PagedList<T>?
) {
    val adapter = recyclerView.adapter as? A
        ?: throw IllegalArgumentException("recyclerView adapter must be a PagedListAdapter!")
    adapter.submitList(itemList)
}

/** Defines the item source of a BaseRecyclerAdapter */
@BindingAdapter("list")
fun <T, A : BaseRecyclerAdapter<T, out ViewDataBinding>> setList(
    recyclerView: RecyclerView,
    itemList: List<T>?
) {
    val adapter = recyclerView.adapter as? A
        ?: throw IllegalArgumentException("recyclerView adapter must be a BaseRecyclerAdapter!")
    adapter.setItems(itemList)
}

/** Loads the image of an [url] into a [imageView] */
@BindingAdapter("avatarUrl")
fun bindLoadAvatar(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .circleCrop()
        .placeholder(R.drawable.ic_avatar)
        .into(imageView)
}

/**
 * Defines the source of a [view]'s [ViewState]
 * Sets the visibility of the view's children based on the current state
 */
@BindingAdapter("state")
fun setState(view: View, state: ViewState) {
    val loadingView: View? = view.findViewById(R.id.loading)
    val emptyView : View? = view.findViewById(R.id.view_empty)

    loadingView?.isVisible = state == ViewState.Loading
    emptyView?.isVisible = state == ViewState.Empty

    if (state is ViewState.Error) {
        Toast.makeText(view.context, state.message, Toast.LENGTH_SHORT).show()
    }
}

/** Converts a [date] to String and sets it as the text of a [textView] */
@BindingAdapter("dateText")
fun setDateText(textView: TextView, date: Date) {
    try {
        val dateFormat = SimpleDateFormat(
            datePattern, Locale.getDefault()
        )
        dateFormat.timeZone = TimeZone.getDefault()
        textView.text = dateFormat.format(date).toLowerCase()
    } catch (exception: Exception) {
        Timber.e(exception)
    }
}

/**
 * Uses a [string] to define the visibility of a [view]
 * The view is only visible when the [string] has an actual content
 */
@BindingAdapter("visibleBy")
fun setVisibilityBy(view: View, string: String?) {
    view.isVisible = !string.isNullOrBlank()
}