package com.example.dannvgithub

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dannvgithub.api.GithubApiServices
import com.example.dannvgithub.database.DatabaseDao
import com.example.dannvgithub.database.convertToGithubUser
import com.example.example.GitHubUserListResponse
import com.example.example.toRoomObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(var githubService: GithubApiServices, var databaseDao: DatabaseDao): ViewModel() {

    var idSince = 0
    var isLoading = MutableStateFlow<Boolean>(true)
    var listUser = listOf<GitHubUserListResponse>()
    fun getListUser() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                Log.d("dannvvvvv", idSince.toString())
                listUser = githubService.getListUser(since = idSince)
                for (i in listUser) {
                    databaseDao.insert(i.toRoomObject())
                }
            } catch (e: Exception) {
                Log.d("dannvvvvv", "idSince")
                listUser = databaseDao.getPerpage(idSince).map { roomUser -> roomUser.convertToGithubUser()}
            }
            if (listUser.size > 0) {
                idSince = listUser.get(listUser.size - 1).id ?: 0
            }
            isLoading.value = false
        }
    }
}