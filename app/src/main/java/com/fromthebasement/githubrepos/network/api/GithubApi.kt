package com.fromthebasement.githubrepos.network.api

import com.fromthebasement.githubrepos.model.PullRequest
import com.fromthebasement.githubrepos.model.RepoDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    suspend fun fetchRepoList(
        @Query(value = "q", encoded = true) keyword : String = "language:Java",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int
    ): RepoDto

    @GET("repos/{author}/{repo}/pulls")
    suspend fun fetchPullRequests(
        @Path(value = "repo") repoName: String,
        @Path(value = "author") authorName: String
    ): List<PullRequest>

}