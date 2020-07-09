package com.fromthebasement.githubrepos.model

import com.squareup.moshi.JsonClass

/**
 * Holds a list of Github Repositories
 */
@JsonClass(generateAdapter = true)
data class RepoDto(
    val items : List<RepoShort>
)