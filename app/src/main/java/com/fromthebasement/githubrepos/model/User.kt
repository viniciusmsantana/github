package com.fromthebasement.githubrepos.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A Github user
 */
@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "login") val name: String,
    @Json(name = "avatar_url") val avatarUrl: String
)