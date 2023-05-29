package com.example.dannvgithub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dannvgithub.api.GithubApiServices
import com.example.dannvgithub.database.DatabaseDao
import com.example.example.GitHubUserListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(var githubService: GithubApiServices, var databaseDao: DatabaseDao): ViewModel() {

    var idSince = 0
    var isLoading = MutableStateFlow<Boolean>(false)
    var listUser = listOf<GitHubUserListResponse>()
    fun getListUser() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                listUser = githubService.getListUser(since = idSince)
                idSince = listUser.get(listUser.size - 1).id ?: 0
            } catch (e: Exception) {

            }

            isLoading.value = false
        }

    }
}