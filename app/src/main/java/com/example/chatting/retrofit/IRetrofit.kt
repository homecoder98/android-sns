package com.example.chatting.retrofit

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET

interface IRetrofit {

    @GET("/friends")
    fun getFriends() : Call<ArrayList<Friends>>
}