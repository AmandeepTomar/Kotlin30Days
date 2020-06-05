package com.example.kotlin30days.repo

import androidx.lifecycle.LiveData
import com.example.kotlin30days.data.local.AppDatabase
import com.example.kotlin30days.data.local.daos.UserDao
import com.example.kotlin30days.data.local.model.User
import com.example.kotlin30days.data.network.api.ApiInterface
import com.example.kotlin30days.utility.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepo @Inject constructor(private val db:AppDatabase,private val dao: UserDao,private val apiInterface: ApiInterface) {


     suspend fun getUsers(): List<User> = withContext(Dispatchers.IO) {

        val list = apiInterface.getUser()
        dao.insertUsers(list)

        Logger.setLog("data received", "Retrieved ${list.size} items from network")

        list
    }


}