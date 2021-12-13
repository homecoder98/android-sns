package com.example.chatting.retrofit

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofitClient : Retrofit? = null

    fun getClient() : Retrofit? {
        if(retrofitClient==null){
            retrofitClient = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return  retrofitClient
    }
}