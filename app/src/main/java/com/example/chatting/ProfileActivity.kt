package com.example.chatting

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.example.chatting.databinding.ActivityMainBinding
import com.example.chatting.databinding.ActivityProfileBinding
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import java.util.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var src = intent.getStringExtra("img")
        Picasso.get().load(src).error(R.drawable.profile).into(binding.imageView)
        binding.nickname.text = intent.getStringExtra("nickname")
        binding.introduce.text = intent.getStringExtra("introduce")

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.imageView.setOnClickListener {
            val imgIntent = Intent(this,ImageActivity::class.java)
            var bitmap : Bitmap = binding.imageView.drawable.toBitmap()
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val bytes = stream.toByteArray()
            imgIntent.putExtra("src", bytes)
            startActivity(imgIntent)
        }
    }

}
