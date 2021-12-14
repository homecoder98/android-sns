package com.example.chatting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatting.adapter.ChattingRoomAdapter
import com.example.chatting.databinding.ActivityChattingRoomBinding
import com.example.chatting.databinding.ActivityMainBinding
import com.example.chatting.db.Profile

class ChattingRoomActivity : AppCompatActivity() {
    private lateinit var binding : ActivityChattingRoomBinding
    private val items = mutableListOf<Profile>()
    public val adepter = ChattingRoomAdapter(items)
    private val OPEN_GALLERY = 1
    private val RESULT_OK = -1
    private val RESULT_CANCELED = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var anonymous = intent.getStringExtra("anonymous")
        binding.anonymous.text = anonymous

        //뒤로가기 버튼 클릭
        binding.finishChatBtn.setOnClickListener {
            finish()
        }

        //이미지 전송 버튼 클릭
        binding.imgBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent,OPEN_GALLERY)
        }

        //메세지 전송 버튼 클릭
        binding.sendBtn.setOnClickListener {
            val msg = binding.msgEdit.text
            items.add(Profile("운영자","남자","25","경기",msg.toString(),""))
            adepter.notifyItemInserted(items.size-1)
        }

        //채팅 리사이클러뷰 세팅
        binding.ChattingRecyclerView.adapter = adepter
        binding.ChattingRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == OPEN_GALLERY)
        {
            if(resultCode == RESULT_OK)
            {
                var currentImageUri = data?.data

                try{
                    currentImageUri?.let {
                        Toast.makeText(this,"가져옴",Toast.LENGTH_SHORT).show()
//                        mBinding!!.profileImage.setImageURI(it)
//                        val path = getFullPathFromUri(layoutInflater.context,it)
//                        Myapplication.prefs.setString("picture",path!!)
                    }
                }catch(e : Exception)
                {
                    e.printStackTrace()
                }
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(layoutInflater.context, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }
}