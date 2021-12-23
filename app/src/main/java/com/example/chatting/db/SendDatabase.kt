package com.example.chatting.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SendFriend::class], version = 1)
abstract class SendDatabase : RoomDatabase(){
    abstract fun sendDao(): SendDao

    companion object{
        private var instance : SendDatabase? = null

        @Synchronized
        fun getInstance(c : Context): SendDatabase? {
            if (instance==null){
                synchronized(SendDatabase::class.java){
                    instance = Room.databaseBuilder(
                        c.applicationContext,
                        SendDatabase::class.java,
                        "send_friend"
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }
}