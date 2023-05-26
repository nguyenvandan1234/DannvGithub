package com.example.dannvgithub.api

import com.example.example.GitHubUserListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface GithubApiServices {

    @Headers("Authorization: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")
    @GET("users")
    suspend fun getListUser(): List<GitHubUserListResponse>
}