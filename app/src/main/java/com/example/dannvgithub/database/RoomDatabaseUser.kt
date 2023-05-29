package com.example.dannvgithub.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.example.GitHubUserListResponse
import com.google.gson.annotations.SerializedName

@Entity
data class RoomDatabaseUser(
    var login: String? = null,
    @PrimaryKey
    var id: Int? = null,
    var nodeId: String? = null,
    var avatarUrl: String? = null,
    var gravatarId: String? = null,
    var url: String? = null,
    var htmlUrl: String? = null,
    var followersUrl: String? = null,
    var followingUrl: String? = null,
    var gistsUrl: String? = null,
    var starredUrl: String? = null,
    var subscriptionsUrl: String? = null,
    var organizationsUrl: String? = null,
    var reposUrl: String? = null,
    var eventsUrl: String? = null,
    var receivedEventsUrl: String? = null,
    var type: String? = null,
    var siteAdmin: Boolean? = null
)

fun RoomDatabaseUser.convertToGithubUser(): GitHubUserListResponse {
    return GitHubUserListResponse(login, id, nodeId, avatarUrl, gravatarId, url, htmlUrl, followersUrl, followingUrl, gistsUrl, starredUrl, subscriptionsUrl, organizationsUrl, reposUrl,
    eventsUrl, receivedEventsUrl, type, siteAdmin)
}