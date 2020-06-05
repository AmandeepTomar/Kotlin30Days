package com.example.kotlin30days.data.network.api

import android.telecom.Call
import androidx.lifecycle.LiveData
import com.example.kotlin30days.data.local.model.User
import com.example.kotlin30days.view.home.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("users")
    suspend fun getUser():List<User>
}