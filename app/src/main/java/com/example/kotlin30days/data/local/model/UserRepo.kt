package com.example.kotlin30days.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "userrepo")
data  class UserRepo(
@PrimaryKey
val id:Long,
val node_id:String,
val name:String,
val full_name:String,
@field:SerializedName("private")
val  isPrivate:Boolean,
val description:String?,
val fork:String?,
val teams_url:String?,
val releases_url:String?,
val stargazers_count:Int?,
val watchers_count:Int?,
val language:String?

){

    fun getUserIdName():String="ID :=$id, Name :=$name"
    fun repoIsPrivate():String =if(isPrivate) "Repository is Private" else "Repository is Public"

    fun getAllUrls():String{
        return "Teams URL :$teams_url\nRelease URL :$releases_url"
    }

    fun getWatchersCount():String="Watchers Count :$watchers_count"
    fun getStrangersCount():String="Stargazers Count :$stargazers_count"

}



