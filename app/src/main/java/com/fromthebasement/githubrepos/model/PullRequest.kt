package com.fromthebasement.githubrepos.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

/**
 * A PullRequest made to a specific Repository
 * [user] author of the PR
 * [title] title of the PR
 * [body] message of the PR
 */
@JsonClass(generateAdapter = true)
data class PullRequest(
    val user: User,
    val title: String,
    val body: String? = null,
    @Json(name = "created_at") val creationDate: Date
)