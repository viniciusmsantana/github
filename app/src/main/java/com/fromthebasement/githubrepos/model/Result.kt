package com.fromthebasement.githubrepos.model

import timber.log.Timber

/**
 * Wrapper class used to get the result of an Api call
 */
sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()

    companion object {
        fun <T> success(data: T): Result<T> = Success(data)
        fun error(message: String): Result<Nothing> = Error(message)
    }
}

suspend fun <T> request(
    call: suspend () -> T
): Result<T> {
    return try {
        Result.success(call())
    } catch (exception: Exception) {
        Timber.e(exception)
        Result.error(exception.message ?: "Unexpected Error")
    }
}