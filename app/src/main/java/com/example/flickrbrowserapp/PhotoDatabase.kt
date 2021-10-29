package com.example.flickrbrowserapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Photo::class],version = 3,exportSchema = false)
abstract class PhotoDatabase:RoomDatabase() {
    companion object{
        @Volatile
        private var INSTANCE: PhotoDatabase? = null

        fun getInstance(context: Context):PhotoDatabase {
            if (INSTANCE != null) {
                return INSTANCE as PhotoDatabase
            }
            synchronized(this){  //for the protection purpose from concurrent execution on multi threading
                val instance = Room.databaseBuilder(context.applicationContext, PhotoDatabase::class.java, "photos"
                ).fallbackToDestructiveMigration()  // Destroys old database on version change
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun photoDao(): PhotoDao

}