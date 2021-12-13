package com.example.chatting.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile")
    fun getAll(): List<Profile>

//    @Query("SELECT * FROM profile WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<Profile>

//    @Query("SELECT * FROM profile WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): Profile

    @Insert
    fun inserProfile(profile: Profile)

    @Delete
    fun delete(user: Profile)
}