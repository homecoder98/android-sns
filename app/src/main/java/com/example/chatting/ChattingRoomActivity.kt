package com.example.chatting

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatting.adapter.ChattingRoomAdapter
import com.example.chatting.databinding.ActivityChattingRoomBinding
import com.example.chatting.databinding.ActivityMainBinding
import com.example.chatting.db.Profile
import com.example.chatting.singlton.mSocket
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.OutputStream


class ChattingRoomActivity : AppCompatActivity() {
    private var socket = mSocket.socket
    private lateinit var binding : ActivityChattingRoomBinding
    private val items = mutableListOf<Profile>()
    val adepter = ChattingRoomAdapter(items)
    private val OPEN_GALLERY = 1
    private val RESULT_OK = -1
    private val RESULT_CANCELED = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var anonymous = intent.getStringExtra("anonymous")
        binding.anonymous.text = anonymous

        //소켓 연결
        mSocket.socket?.connect()

        socket?.on(Socket.EVENT_CONNECT, Emitter.Listener {
            Log.d("socket",it.toString())
            socket?.emit("entry_user",anonymous)
        })

        socket?.on("img_send", Emitter.Listener {
//            val imageBytes = Base64.decode(it[0].toString(), Base64.NO_WRAP)
//            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            runOnUiThread {
                //image apply
                items.add(Profile("운영자","남자","25","경기","", it[0].toString()))
                adepter.notifyItemInserted(items.size-1)
//                binding..setImageBitmap(decodedImage)
            }
        })

        //뒤로가기 버튼 클릭
        binding.finishChatBtn.setOnClickListener {
            finish()
        }

        //이미지 전송 버튼 클릭
        binding.imgBtn.setOnClickListener {
//            socket?.emit("img","a")
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent,OPEN_GALLERY)
        }

        //메세지 전송 버튼 클릭
        binding.sendBtn.setOnClickListener {
            val msg = binding.msgEdit.text
            items.add(Profile("운영자","남자","25","경기",msg.toString(),"",false))
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
                        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
                        val os = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,os)
                        val bytes = os.toByteArray()
                        val p = Base64.encodeToString(bytes,Base64.NO_WRAP)
                        items.add(Profile("운영자","남자","25","경기","",p.toString(),true))
                        adepter.notifyItemInserted(items.size-1)
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

    override fun onDestroy() {
        socket?.disconnect()
        socket = null
        super.onDestroy()
    }
}