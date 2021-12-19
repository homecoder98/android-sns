package com.example.chatting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.chatting.databinding.ActivityMainBinding
import com.example.chatting.db.AppDatabase
import com.example.chatting.singlton.mSocket
import io.socket.emitter.Emitter

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var db : AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val socket = mSocket.socket
        socket?.connect()
        socket?.on("msg", Emitter.Listener {
            Log.d("socket",it[0].toString())
        })

        //메인 화면에 네비게이션 붙이기
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.myBottomNav,navController)

        //db에서 프로필 정보 가져와 메모리에 올리기
//        CoroutineScope(Dispatchers.IO).launch{
//            val profile = db!!.profileDao().getAll()
//            Log.d("profile",profile.toString())
//        }


    }

    override fun onDestroy() {
        mSocket.socket?.disconnect()
        super.onDestroy()
        if(db!=null)
            db = null
    }
}