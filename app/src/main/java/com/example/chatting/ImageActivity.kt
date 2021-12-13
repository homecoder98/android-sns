package com.example.chatting

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import androidx.annotation.UiThread
import androidx.core.graphics.drawable.toDrawable
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.chatting.adapter.FriendsAdapter
import com.example.chatting.model.FriendsModel
import com.example.chatting.retrofit.Friends
import com.example.chatting.retrofit.IRetrofit
import com.example.chatting.retrofit.RetrofitClient
import com.squareup.picasso.Picasso
import okio.Utf8
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.net.URL
import java.nio.charset.Charset

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        //인텐트로 받은 비트맵 이미지 붙이기
        val picture = findViewById<ImageView>(R.id.picture)
        var drawable = intent.getByteArrayExtra("src")
        var bitmap = BitmapFactory.decodeByteArray(drawable,0,drawable!!.size)
        picture.setImageBitmap(bitmap)

//        val retrofit = RetrofitClient.getClient()
//        val service = retrofit?.create(IRetrofit::class.java)
//
//        service!!.getFriends().enqueue(object : Callback<Friends> {
//            override fun onResponse(call: Call<Friends>, response: Response<Friends>) {
//
//                response.body()?.let {
//                    val nickname = it.nickname
//                    val sex = it.sex
//                    val age = it.age
//                    val local = it.local
//                    val introduce = it.introduce
//                    val profile = it.profile
//
//                    Picasso.get().load(profile.toUri()).into(picture)
//
//                }
//            }
//
//            override fun onFailure(call: Call<Friends>, t: Throwable) {
//                Log.d("data","fail")
//            }
//        })

    }
}