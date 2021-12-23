package com.example.chatting.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "send_friend")
data class SendFriend(
    @PrimaryKey
    var nickname: String,
    var is_send:Boolean = false
)
