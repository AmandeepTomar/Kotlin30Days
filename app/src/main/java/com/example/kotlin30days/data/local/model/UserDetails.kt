package com.example.kotlin30days.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class UserDetails(
    @field:SerializedName("id")
    val id: Int,
    @PrimaryKey
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("html_url")
    val html_url: String,
    @field:SerializedName("avatar_url")
    val avatarUrl: String?,
    @field:SerializedName("node_id")
    val node_id: String?,
    @field:SerializedName("url")
    val company: String?,
    @field:SerializedName("repos_url")
    val reposUrl: String?,
    @field:SerializedName("gists_url")
    val gists_url: String?,
    @field:SerializedName("followers_url")
    val followers_url: String?,
    @field:SerializedName("following_url")
    val following_url: String?,
    @field:SerializedName("starred_url")
    val starred_url: String?,
    @field:SerializedName("subscriptions_url")
    val subscriptions_url: String?,
    @field:SerializedName("organizations_url")
    val organizations_url: String?,
    @field:SerializedName("blog")
    val blog: String?,
    @field:SerializedName("location")
    val location: String?,
    @field:SerializedName("email")
    val email: String?,
    @field:SerializedName("bio")
    val bio: String?,
    @field:SerializedName("twitter_username")
    val twitter_username: String?,
    @field:SerializedName("public_repos")
    val public_repos: Int,
    @field:SerializedName("public_gists")
    val public_gists: Int,
    @field:SerializedName("followers")
    val followers: Int,
    @field:SerializedName("following")
    val following: Int?,
    @field:SerializedName("created_at")
    val created_at: String?,
    @field:SerializedName("updated_at")
    val updated_at: String?

)


