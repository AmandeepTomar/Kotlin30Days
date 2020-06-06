package com.example.kotlin30days.di

import android.app.Application
import androidx.room.Room
import com.example.kotlin30days.data.local.AppDatabase
import com.example.kotlin30days.data.local.daos.UserDao
import com.example.kotlin30days.data.local.daos.UserDetailsDao
import com.example.kotlin30days.data.local.daos.UserRepoDaos
import com.example.kotlin30days.utility.AppPref
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDBModule {

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): AppDatabase {
        return AppDatabase.getInstance(application)
    }

    @Provides
    @Singleton
    internal fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    internal fun provideUserRepoDao(appDatabase: AppDatabase): UserRepoDaos {
        return appDatabase.userRepoDao()
    }

    @Provides
    @Singleton
    internal fun provideUserDetailsDao(appDatabase: AppDatabase): UserDetailsDao {
        return appDatabase.userDetailsDao()
    }

    @Provides
    @Singleton
    internal fun provideSharedPref(application: Application): AppPref{
        return AppPref(application)
    }

}