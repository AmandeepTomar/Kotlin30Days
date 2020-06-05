package com.example.kotlin30days.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlin30days.data.local.daos.UserDao
import com.example.kotlin30days.data.local.model.User

@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
     public abstract fun userDao(): UserDao
}