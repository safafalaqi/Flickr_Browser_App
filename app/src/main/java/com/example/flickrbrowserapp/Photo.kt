package com.example.flickrbrowserapp

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Photos")
data class Photo(
    @SerializedName("farm")
    val farm: Int?=null,
    @SerializedName("id")
    @PrimaryKey
    val id: String,
    @SerializedName("isfamily")
     val isfamily: Int?=null,
    @SerializedName("isfriend")
     val isfriend: Int?=null,
    @SerializedName("ispublic")
    val ispublic: Int?=null,
    @SerializedName("owner")
    val owner: String?=null,
    @SerializedName("secret")
    val secret: String?=null,
    @SerializedName("server")
    val server: String?=null,
    @SerializedName("title")
    val title: String?=null,
    var checked: Boolean?=null

)