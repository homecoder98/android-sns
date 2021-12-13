package com.example.chatting.db

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey
    var nickname: String,
    var sex: String,
    var age: String,
    var local: String,
    @Nullable
    var introduce: String?,
    @Nullable
    var profile: String?
)
