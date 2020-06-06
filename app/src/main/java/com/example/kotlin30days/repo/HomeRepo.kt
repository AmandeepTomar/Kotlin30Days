package com.example.kotlin30days.repo

import com.example.kotlin30days.data.local.AppDatabase
import com.example.kotlin30days.data.local.daos.UserDao
import com.example.kotlin30days.data.local.model.Users
import com.example.kotlin30days.data.local.model.UserRepo
import com.example.kotlin30days.data.network.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepo @Inject constructor(private val db:AppDatabase,private val dao: UserDao,private val apiInterface: ApiInterface) {

    @Throws(Exception::class)
     suspend fun getUsers(): List<Users> = withContext(Dispatchers.IO) {
        val list = apiInterface.getUser()
        dao.insertUsers(list)

        list
    }

    @Throws(Exception::class)
    suspend fun getUsersRepo(login:String): List<UserRepo> = withContext(Dispatchers.IO) {
        val list = apiInterface.getRepos(login)
        db.userRepoDao().insertRepos(list)

        list
    }

    @Throws(Exception::class)
    suspend fun getUserDetails(login:String): List<UserRepo> = withContext(Dispatchers.IO) {
        val list = apiInterface.getRepos(login)
        db.userRepoDao().insertRepos(list)

        list
    }


}