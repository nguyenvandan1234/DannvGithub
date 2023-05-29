package com.example.dannvgithub.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.example.GitHubUserListResponse

@Dao
interface DatabaseDao {
    @Query("SELECT * FROM RoomDatabaseUser")
    fun getAll(): List<RoomDatabaseUser>

    @Insert
    fun insertAll(vararg users: RoomDatabaseUser)

    @Insert
    fun insert(user: RoomDatabaseUser)

    @Delete
    fun delete(user: RoomDatabaseUser)
}