package com.fromthebasement.githubrepos.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A Github Repository
 */
@JsonClass(generateAdapter = true)
data class RepoShort(
    @Json(name = "html_url") val url: String,
    @Json(name = "stargazers_count") val stars: String,
    @Json(name = "forks_count") val forks: String,
    val id: Long,
    val owner: User,
    val name: String,
    val description : String? = null
)