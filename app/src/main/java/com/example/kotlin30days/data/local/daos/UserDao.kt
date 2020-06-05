package com.example.kotlin30days.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlin30days.data.local.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(contributors: List<User>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)

    @Query("SELECT * FROM user WHERE login =:login")
    fun findByLogin(login: String): LiveData<User>

    @Query("SELECT * FROM user")
    fun getAllUsers():List<User>



}