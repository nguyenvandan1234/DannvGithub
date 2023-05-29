package com.example.dannvgithub.database

import androidx.room.*
import com.example.example.GitHubUserListResponse

@Dao
interface DatabaseDao {
    @Query("SELECT * FROM RoomDatabaseUser")
    suspend fun getAll(): List<RoomDatabaseUser>

    @Query("SELECT * FROM RoomDatabaseUser where id > (:lastId) LIMIT 10")
    suspend fun getPerpage(lastId: Int): List<RoomDatabaseUser>

    @Insert
    suspend fun insertAll(vararg users: RoomDatabaseUser)

    @Insert
    suspend fun insert(user: RoomDatabaseUser)

    @Delete
    suspend fun delete(user: RoomDatabaseUser)
}