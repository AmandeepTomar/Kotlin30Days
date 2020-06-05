package com.example.kotlin30days.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(

    @field:SerializedName("id")
    val id: Int,
    @PrimaryKey
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("avatar_url")
    val avatarUrl: String?,
    @field:SerializedName("node_id")
    val node_id: String?,
    @field:SerializedName("url")
    val company: String?,
    @field:SerializedName("repos_url")
    val reposUrl: String?,
    @field:SerializedName("gists_url")
    val gists_url: String?
)