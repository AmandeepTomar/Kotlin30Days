package com.example.kotlin30days.data.local.model

import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlin30days.utility.setImage
import com.google.gson.annotations.SerializedName
import de.hdodenhof.circleimageview.CircleImageView

@Entity
data class Users(

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
    val gists_url: String?



){
   companion object{
       @BindingAdapter("profileImage")
       public fun loadImage(view:CircleImageView,  imageUrl:String) {
           view.setImage(url = imageUrl)
       }
   }
}