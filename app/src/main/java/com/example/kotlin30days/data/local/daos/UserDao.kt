package com.example.kotlin30days.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlin30days.data.local.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM user WHERE login = :login")
    fun findByLogin(login: String): LiveData<User>






}