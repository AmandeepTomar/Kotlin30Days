package com.example.kotlin30days.data.network.api

import com.example.kotlin30days.data.local.model.UserDetails
import com.example.kotlin30days.data.local.model.Users
import com.example.kotlin30days.data.local.model.UserRepo
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("users")
    suspend fun getUser():List<Users>

    @GET("users/{login}/repos")
   suspend fun getRepos(@Path("login") login: String): List<UserRepo>


    @GET("users/{login}")
   suspend fun getUser(@Path("login") login: String):UserDetails
}