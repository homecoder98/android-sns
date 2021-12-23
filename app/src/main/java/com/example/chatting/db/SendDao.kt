package com.example.chatting.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SendDao {
    @Query("SELECT * FROM send_friend")
    fun getAll(): List<SendFriend>

    @Query("SELECT * FROM send_friend WHERE nickname = :nickname")
    fun getSendFriend(nickname: String): SendFriend

//    @Query("SELECT * FROM profile WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): Profile

    @Insert
    fun inserSendFriend(send_friend: SendFriend)

    @Delete
    fun delete(send_friend: SendFriend)
}