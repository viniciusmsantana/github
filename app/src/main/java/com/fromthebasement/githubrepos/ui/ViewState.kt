package com.fromthebasement.githubrepos.ui

/**
 * Class used to map every possible state of a view
 */
sealed class ViewState {
    object Loading : ViewState()
    object Content : ViewState()
    object Empty : ViewState()
    data class Error(val message: String) : ViewState()
}