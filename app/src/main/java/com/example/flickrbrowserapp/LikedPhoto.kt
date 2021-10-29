package com.example.flickrbrowserapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class LikedPhoto(

    val id: String,
    val farm: Int,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String,
    var checked: Boolean
)