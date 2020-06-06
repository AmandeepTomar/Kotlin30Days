package com.example.kotlin30days.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlin30days.data.local.daos.UserDao
import com.example.kotlin30days.data.local.daos.UserDetailsDao
import com.example.kotlin30days.data.local.daos.UserRepoDaos
import com.example.kotlin30days.data.local.model.UserDetails
import com.example.kotlin30days.data.local.model.UserRepo
import com.example.kotlin30days.data.local.model.Users

@Database(entities = [Users::class,UserRepo::class,UserDetails::class],version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

      abstract fun userDao(): UserDao
      abstract fun userRepoDao():UserRepoDaos
      abstract fun userDetailsDao():UserDetailsDao

      companion object {

            @Volatile
            private var INSTANCE: AppDatabase? = null

            fun getInstance(context: Application): AppDatabase = INSTANCE ?: synchronized(this) {
                  INSTANCE ?: createDatabase(context).also { INSTANCE = it }
            }

            private fun createDatabase(context: Application) =
                  Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
      }
}

const val DB_NAME="30daysKotlin.db"