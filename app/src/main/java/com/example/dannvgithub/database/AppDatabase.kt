package com.example.dannvgithub.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.example.GitHubUserListResponse

@Database(entities = [RoomDatabaseUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): DatabaseDao
}