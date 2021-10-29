package com.example.flickrbrowserapp

import androidx.room.*

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPhoto(photo: Photo)

    @Query("SELECT * FROM Photos")
    fun getPhotos(): List<Photo>

    @Delete
    suspend fun deletePhoto(photo: Photo)

}