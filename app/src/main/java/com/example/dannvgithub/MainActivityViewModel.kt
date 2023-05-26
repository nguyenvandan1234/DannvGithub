package com.example.dannvgithub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dannvgithub.api.GithubApi
import com.example.example.GitHubUserListResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(var apiGithubApi: GithubApi = GithubApi.getInstanceApi()): ViewModel() {

    var isLoading = MutableStateFlow<Boolean>(false)
    var listUser = listOf<GitHubUserListResponse>()
    fun getListUser() {
        isLoading.value = true
        viewModelScope.launch {
            listUser = apiGithubApi.service.getListUser()
            isLoading.value = false
        }
    }
}