package com.example.chatting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.chatting.databinding.ActivityMainBinding
import com.example.chatting.db.AppDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var db : AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        super.onDestroy()
        if(db!=null)
            db = null
    }
}