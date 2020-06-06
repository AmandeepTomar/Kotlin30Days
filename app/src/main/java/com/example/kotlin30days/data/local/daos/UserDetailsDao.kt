package com.example.kotlin30days.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin30days.data.local.model.UserDetails

@Dao
interface UserDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetRepo(user: UserDetails)


    @Query("SELECT * FROM userdetails WHERE login =:login")
    fun findByLogin(login: String): LiveData<UserDetails>
}