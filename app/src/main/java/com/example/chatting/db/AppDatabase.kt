package com.example.chatting.db

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Profile::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object{
        private var instance : AppDatabase? = null

        @Synchronized
        fun getInstance(c : Context): AppDatabase? {
            if (instance==null){
                synchronized(AppDatabase::class.java){
                    instance = Room.databaseBuilder(
                        c.applicationContext,
                        AppDatabase::class.java,
                        "profile"
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }
}