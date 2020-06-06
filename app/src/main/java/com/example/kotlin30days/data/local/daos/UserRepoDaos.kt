package com.example.kotlin30days.data.local.daos

import androidx.room.*
import com.example.kotlin30days.data.local.model.UserRepo

@Dao
interface UserRepoDaos {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetRepo(user: UserRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(contributors: List<UserRepo>)


    @Query("SELECT * FROM userrepo")
    fun getAllRepos():List<UserRepo>
}