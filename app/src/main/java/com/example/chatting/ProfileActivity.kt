package com.example.chatting

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.example.chatting.databinding.ActivityMainBinding
import com.example.chatting.databinding.ActivityProfileBinding
import com.example.chatting.db.SendDatabase
import com.example.chatting.db.SendFriend
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import java.util.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProfileBinding
    private var db : SendDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var src = intent.getStringExtra("img")
        Picasso.get().load(src).error(R.drawable.profile).into(binding.imageView)
        binding.nickname.text = intent.getStringExtra("nickname")
        binding.introduce.text = intent.getStringExtra("introduce")

        //db에서 친구신청 여부 가져와서 있으면 이미 친구신청을 보냈습니다 뷰 생성+edit,button invisible
        db = SendDatabase.getInstance(this)
        db?.sendDao()?.getSendFriend(binding.nickname.text.toString()).let {
            if(it!=null){
                if(it!!.is_send){
                    binding.isSendFriend.visibility = View.VISIBLE
                    binding.sendFriendContainer.visibility = View.GONE
                }
            }
        }

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.sendFriendButton.setOnClickListener {
            val msg = binding.sendFriendEdit.text.toString()
            db!!.sendDao().inserSendFriend(SendFriend(binding.nickname.text.toString(),true))
            //서버로 친구신청 요청 아직 미개발

            binding.isSendFriend.visibility = View.VISIBLE
            binding.sendFriendContainer.visibility = View.GONE
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
