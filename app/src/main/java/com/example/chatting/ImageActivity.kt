package com.example.chatting

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        //인텐트로 받은 비트맵 이미지 붙이기
        val picture = findViewById<ImageView>(R.id.picture)
        var drawable = intent.getByteArrayExtra("src")
        var bitmap = BitmapFactory.decodeByteArray(drawable,0,drawable!!.size)
        picture.setImageBitmap(bitmap)

    }
}