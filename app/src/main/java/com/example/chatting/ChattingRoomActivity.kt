package com.example.chatting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ChattingRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting_room)

        val Anonymous = findViewById<TextView>(R.id.anonymous)
        var anonymous = intent.getStringExtra("anonymous")
        Anonymous.text = anonymous

        val backBtn = findViewById<Button>(R.id.finish_chat_btn)
        backBtn.setOnClickListener {
            finish()
        }
    }
}