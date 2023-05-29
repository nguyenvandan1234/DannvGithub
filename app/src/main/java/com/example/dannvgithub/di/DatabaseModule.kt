package com.example.dannvgithub.di

import android.app.Application
import android.content.Context
import androidx.room.Entity
import androidx.room.Room
import com.example.dannvgithub.MyApplication
import com.example.dannvgithub.database.AppDatabase
import com.example.dannvgithub.database.DatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

//    @Singleton
//    @Provides
//    fun providerDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
//        return Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "dannv.db"
//        ).build()
//    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase{
        return Room.databaseBuilder(application, AppDatabase::class.java, "news_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providerDatabaseDao(appDatabase: AppDatabase): DatabaseDao {
        return appDatabase.userDao()
    }
}