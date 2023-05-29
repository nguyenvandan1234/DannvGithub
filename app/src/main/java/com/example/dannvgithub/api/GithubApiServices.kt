package com.example.dannvgithub.api

import com.example.example.GitHubUserListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubApiServices {

    @Headers("Authorization: Bearer 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")
    @GET("users")
    suspend fun getListUser(@Query ("per_page") perPage: Int = 30,
    @Query("since") since: Int): List<GitHubUserListResponse>
}